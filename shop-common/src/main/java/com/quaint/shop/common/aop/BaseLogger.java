package com.quaint.shop.common.aop;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * <p>
 * desc: 基础logger
 * </p>
 *
 * @author quaint
 * @since 01 May 2020
 */
public abstract class BaseLogger {

    @Value("${spring.application.name}")
    String serverName;

    protected static final Logger log = LoggerFactory.getLogger(BaseLogger.class);

    protected String loggerPreprocessor(ProceedingJoinPoint joinPoint, HttpServletRequest request){

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

        return apiPath;
    }

}
