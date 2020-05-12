package com.quaint.shop.member.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * desc: 登录校验
 * </p>
 *
 * @author quaint
 * @since 29 April 2020
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AopLogin {

    /**
     * 是否开启登录校验
     * @return bool
     */
    boolean open() default false;

    /**
     * 如果没有开启的话, 默认用第一个用户
     * @return id
     */
    long test() default 1L;

}
