package com.quaint.shop.common.aop;

import com.alibaba.fastjson.JSON;
import com.quaint.shop.common.dto.SeekIdeaResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

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
public class LoggerResultAspect extends BaseLogger{

    /**
     * 定义切点
     * 1. public    ==>   范围
     * 2. *         ==>   返回类型
     * 3. 包目录.*   ==>   该包下的所有controller
     * 4. *(..)     ==>   所有方法
     * // @Pointcut("execution(public * com.quaint.shop.member.spi.aopspi.*.*(..))")
     */
    @Pointcut("@annotation(com.quaint.shop.common.annotation.AopLoggerResult)")
    public void aspect() {
        System.out.println("aspect");
    }

    @Around("aspect()")
    public void around(ProceedingJoinPoint joinPoint) {

        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = sra.getResponse();
        response.setContentType("application/json;charset=utf-8");
        HttpServletRequest request = sra.getRequest();

        // 获取 请求信息
        String apiPath = this.loggerPreprocessor(joinPoint, request);
        // 请求地址 和 方法名称
        String method = joinPoint.getSignature().getName();

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