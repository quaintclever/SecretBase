package com.quaint.shop.member.aop;

import com.quaint.shop.member.annotation.AopLogin;
import com.quaint.shop.member.api.MemberInfoApi;
import com.quaint.shop.member.dto.info.MemberInfoDto;
import com.quaint.shop.member.helper.MemberContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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
    MemberInfoApi memberInfoApi;

    @Around(value = "@annotation(com.quaint.shop.member.annotation.AopLogin) && @annotation(aopLogin)"
            ,argNames = "joinPoint,aopLogin")
    public Object around(ProceedingJoinPoint joinPoint, AopLogin aopLogin) throws Throwable {

        MemberContext.clean();
        String token;
        // 注解是否开启, 未开启使用test 里面的id
        if (aopLogin.open()){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = (String) request.getSession().getAttribute("token");
            if (StringUtils.isEmpty(token)){
                throw new RuntimeException("用户未登录!");
            }
        } else {
            token = "token#"+ aopLogin.test();
        }

        // 解析token
        try {
            Long id = Long.parseLong(token.substring(token.lastIndexOf("#") + 1));
            MemberInfoDto user = memberInfoApi.getMemberInfoById(id);
            if (user == null || user.getId() == null) {
                throw new RuntimeException("用户信息不存在!");
            }
            // 设置用户信息到上下文中
            MemberContext.setMemberId(user.getId());
        } catch (Exception e) {
            throw new RuntimeException("用户未登录!");
        }

        return joinPoint.proceed();

    }

}
