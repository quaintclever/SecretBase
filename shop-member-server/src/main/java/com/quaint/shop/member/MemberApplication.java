package com.quaint.shop.member;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * desc: 启动类
 * </p>
 *
 * @author quaint
 * @since 26 April 2020
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.quaint.shop")
public class MemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class,args);
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
