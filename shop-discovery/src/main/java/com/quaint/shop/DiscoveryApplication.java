package com.quaint.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author quaint
 * @since 30 April 2020
 */
@SpringBootApplication
@EnableDiscoveryClient
public class DiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplication.class, args);
    }

}
