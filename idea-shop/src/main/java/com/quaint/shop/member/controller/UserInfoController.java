package com.quaint.shop.member.controller;

import com.quaint.shop.common.annotation.CheckLogin;
import com.quaint.shop.common.helper.UserContext;
import com.quaint.shop.member.dto.info.UserInfoDto;
import com.quaint.shop.member.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("userInfo")
public class UserInfoController {

    @Autowired
    UserInfoService userInfoService;

    @ApiOperation("获取当前登录用户的信息")
    @PostMapping("current")
    @CheckLogin
    public UserInfoDto getUserInfo(){
        Long userId = UserContext.getUserId();
        return userInfoService.getUserInfoById(userId);
    }


}

