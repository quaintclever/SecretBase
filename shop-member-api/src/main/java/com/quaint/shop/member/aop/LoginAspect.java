package com.quaint.shop.member.aop;

import com.quaint.shop.member.api.UserInfoApi;
import com.quaint.shop.member.dto.info.UserInfoDto;
import com.quaint.shop.member.helper.UserContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * desc: 登录拦截
 * </p>
 *
 * @author quaint
 * @since 29 April 2020
 */
@Aspect
@Component
@Order(2)
public class LoginAspect {

    private static final Logger log = LoggerFactory.getLogger(LoginAspect.class);

    @Autowired
    UserInfoApi userInfoApi;

    @Pointcut("@annotation(com.quaint.shop.member.annotation.CheckLogin)")
    public void login() {
    }

    @Around("login()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionToken = (String) request.getSession().getAttribute("token");
        // todo 假设session token 是 下面的 token
        String token = "session -> token -> jwt -> id:1";
        // todo 假设解析token
        try {
            Long id = Long.parseLong(token.substring(token.lastIndexOf(":") + 1));
            UserInfoDto user = userInfoApi.getUserInfoById(id);
            if (user == null) {
                throw new RuntimeException("用户信息不存在!");
            }
            // 设置用户信息到上下文中
            UserContext.setUserId(user.getId());
        } catch (NumberFormatException e) {
            throw new RuntimeException("用户未登录!");
        }

        return joinPoint.proceed();

    }

}
