package com.shop.spike;

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
public class SpikeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpikeApplication.class,args);
    }

}
