package com.quaint.shop.spike;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author quaint
 * @since 30 April 2020
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.quaint.shop")
@EnableAsync
public class SpikeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpikeApplication.class,args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    /**
     * 初始化负载均衡策略, 或者在配置文件中配置
     * @return iRule
     */
    @Bean
    public IRule getIRule(){
        return new RandomRule();
    }

}
