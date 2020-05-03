package com.quaint.shop.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * desc: aop 日志拦截注解, 需要启动类配置 扫描
 * 会把 对象自动封装为 ShopResult 对象
 * @ ComponentScan(basePackages = "com.quaint.shop")
 * </p>
 *
 * @author quaint
 * @since 30 April 2020
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AopLoggerResult {

}
