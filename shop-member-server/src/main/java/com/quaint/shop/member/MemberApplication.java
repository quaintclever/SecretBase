package com.quaint.shop.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

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
public class MemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class,args);
    }

}
