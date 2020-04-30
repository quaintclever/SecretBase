package com.quaint.shop.spike.controller;

import com.quaint.shop.common.annotation.AopLogger;
import com.quaint.shop.member.annotation.AopLogin;
import com.quaint.shop.member.api.UserInfoApi;
import com.quaint.shop.member.dto.info.UserInfoDto;
import com.quaint.shop.member.helper.UserContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author quaint
 * @since 30 April 2020
 */
@Api(tags = {"测试获取用户信息","分类: 测试"})
@RestController
public class TestController {

    @Autowired
    UserInfoApi userInfoApi;

    @PostMapping("test")
    @AopLogger
    @AopLogin
    @ApiOperation("测试获取用户信息")
    public UserInfoDto testGetUserInfo(){
        Long userId = UserContext.getUserId();
        return userInfoApi.getUserInfoById(userId);
    }

}
