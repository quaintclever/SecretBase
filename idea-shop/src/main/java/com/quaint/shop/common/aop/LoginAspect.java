package com.quaint.shop.common.aop;

import com.quaint.shop.common.helper.UserContext;
import com.quaint.shop.member.dao.UserInfoMapper;
import com.quaint.shop.member.po.UserInfoPo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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
@Slf4j
@Order(2)
public class LoginAspect {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Pointcut("@annotation(com.quaint.shop.common.annotation.CheckLogin)")
    public void login(){}

    @Around("login()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionToken = (String) request.getSession().getAttribute("token");
        // todo 假设session token 是 下面的 token
        String token = "session -> token -> jwt -> id:1";
        // todo 假设解析token
        try {
            Long id = Long.parseLong(token.substring(token.lastIndexOf(":")+1));
            UserInfoPo userInfoPo = userInfoMapper.selectById(id);
            if (userInfoPo == null){
                throw new RuntimeException("用户信息不存在!");
            }
            // 设置用户信息到上下文中
            UserContext.setUserId(userInfoPo.getId());
        } catch (NumberFormatException e) {
            throw new RuntimeException("用户未登录!");
        }

        return joinPoint.proceed();

    }

}
