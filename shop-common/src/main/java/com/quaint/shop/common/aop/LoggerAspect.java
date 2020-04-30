package com.quaint.shop.common.aop;

import com.alibaba.fastjson.JSON;
import com.quaint.shop.common.dto.SeekIdeaResult;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * <p>
 * desc: aop 实现 接口日志
 * </p>
 *
 * @author quaint
 */
@Aspect
@Component
@Order(1)
public class LoggerAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggerAspect.class);

    @Value("${spring.application.name}")
    String serverName;

    /**
     * 定义切点
     * 1. public    ==>   范围
     * 2. *         ==>   返回类型
     * 3. 包目录.*   ==>   该包下的所有controller
     * 4. *(..)     ==>   所有方法
     * // @Pointcut("execution(public * com.quaint.shop.member.spi.aopspi.*.*(..))")
     */
    @Pointcut("@annotation(com.quaint.shop.common.annotation.AopLogger)")
    public void aspect() {
        System.out.println("aspect");
    }

    @Around("aspect()")
    public void around(ProceedingJoinPoint joinPoint) {
        // 获取 request
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        HttpServletResponse response = sra.getResponse();
        response.setContentType("application/json;charset=utf-8");

        // 请求地址 和 方法名称
        String apiPath = serverName + request.getServletPath();
        String method = joinPoint.getSignature().getName();

        // 拼接入参
        StringBuilder paramStr = new StringBuilder();
        Object[] paramsArray = joinPoint.getArgs();
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                if (o instanceof Serializable) {
                    paramStr.append(o.toString()).append(",");
                } else {
                    //使用json系列化 反射等等方法 反系列化会影响请求性能建议重写toString方法实现系列化接口
                    String param = JSON.toJSONString(o);
                    if (StringUtils.isNotBlank(param)) {
                        paramStr.append(param).append(",");
                    }
                }
            }
        }
        boolean hasParam = paramStr.length() > 0;

        // 方法执行前打印日志
        log.info("【接口请求地址】==> [{}],【请求方法】==> [{}({})]", apiPath, method, hasParam ? paramStr.substring(0, paramStr.length() - 1) : "");

        SeekIdeaResult<Object> ideaResult = null;
        PrintWriter resultWriter = null;
        try {
            resultWriter = response.getWriter();
            Object result = joinPoint.proceed();
            if (result instanceof SeekIdeaResult) {
                ideaResult = (SeekIdeaResult) result;
            } else {
                ideaResult = new SeekIdeaResult<>();
                ideaResult.setBody(result);
            }
            ideaResult.setMsg("success");
            ideaResult.setCode(HttpStatus.OK.value());
            String jsonResult = JSON.toJSONString(ideaResult);
            resultWriter.write(jsonResult);
            log.info("【接口请求地址】==> [{}],【请求成功】==> result:[{}]", apiPath, jsonResult);
        } catch (Throwable throwable) {
            if (throwable instanceof Exception) {
                Exception e = (Exception) throwable;
                log.info("【接口请求地址】==> [{}],【请求错误原因】==> {}:[{}{}]", apiPath, method, e.getCause(), e.getMessage());
                if (ideaResult == null) {
                    ideaResult = new SeekIdeaResult<>();
                }
                ideaResult.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                ideaResult.setMsg("接口请求异常==>" + e.getMessage());
                if (resultWriter != null) {
                    resultWriter.write(JSON.toJSONString(ideaResult));
                }
            } else {
                throwable.printStackTrace();
            }
        }

    }

}