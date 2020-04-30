package com.quaint.shop.member.controller;

import com.quaint.shop.common.annotation.AopLogger;
import com.quaint.shop.common.dto.SeekIdeaResult;
import com.quaint.shop.member.annotation.AopLogin;
import com.quaint.shop.member.api.UserInfoApi;
import com.quaint.shop.member.dto.info.UserInfoDto;
import com.quaint.shop.member.helper.UserContext;
import com.quaint.shop.member.server.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * desc: 用户信息相关 controller
 * </p>
 *
 * @author quaint
 * @since 29 April 2020
 */
@Api(tags = {"用户信息相关接口","分类: 用户"})
@RestController
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @ApiOperation("获取当前登录用户的信息")
    @PostMapping("userInfo/current")
    @AopLogin
    @AopLogger
    public UserInfoDto getUserInfo(){
        Long userId = UserContext.getUserId();
        return userInfoService.getUserInfoById(userId);
    }

}

