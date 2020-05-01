package com.quaint.shop.member.api.impl;

import com.quaint.shop.member.api.UserInfoApi;
import com.quaint.shop.member.constant.UserApiUrlConstants;
import com.quaint.shop.member.dto.info.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author quaint
 * @since 30 April 2020
 */
@Component
public class UserInfoApiImpl implements UserInfoApi {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Override
    public UserInfoDto getUserInfoById(Long id) {
        // 通过负载均衡获取实例
        ServiceInstance choose = loadBalancerClient.choose("shop-member-server");
        String url = "http://shop-member-server:"+choose.getPort()+ UserApiUrlConstants.GET_USER_INFO_BY_ID;
        return restTemplate.postForObject(url, id,UserInfoDto.class);
    }
}
