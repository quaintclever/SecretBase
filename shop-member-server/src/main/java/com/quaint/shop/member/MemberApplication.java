package com.quaint.shop.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>
 * desc: 启动类
 * </p>
 *
 * @author quaint
 * @since 26 April 2020
 */
@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = "com.quaint.shop")
public class MemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class,args);
    }

}
