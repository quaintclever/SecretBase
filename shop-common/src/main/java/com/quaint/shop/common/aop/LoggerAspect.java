package com.quaint.shop.common.aop;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * <p>
 * desc: aop 实现 接口日志 (非封装, api 接口使用)
 * </p>
 *
 * @author quaint
 */
@Aspect
@Order(1)
@Component
public class LoggerAspect extends BaseLogger{

    @Pointcut("@annotation(com.quaint.shop.common.annotation.AopLogger)")
    public void aspect() {
        System.out.println("aspect");
    }

    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint) {

        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();

        // 获取 请求信息
        String apiPath = this.loggerPreprocessor(joinPoint,request);
        // 请求地址 和 方法名称
        String method = joinPoint.getSignature().getName();

        Object result = null;
        try {
            result = joinPoint.proceed();
            String jsonResult = JSON.toJSONString(result);
            log.info("【接口请求地址】==> [{}],【请求成功】==> result:[{}]", apiPath, jsonResult);
        } catch (Throwable throwable) {
            if (throwable instanceof Exception) {
                Exception e = (Exception) throwable;
                log.info("【接口请求地址】==> [{}],【请求错误原因】==> {}:[{}{}]", apiPath, method, e.getCause(), e.getMessage());
            } else {
                throwable.printStackTrace();
            }
        }
        return result;

    }

}