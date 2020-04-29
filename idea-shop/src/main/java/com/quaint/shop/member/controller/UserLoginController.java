package com.quaint.shop.member.controller;

import com.quaint.shop.member.dto.login.SendCode;
import com.quaint.shop.member.dto.login.UserLogin;
import com.quaint.shop.member.enums.SendCodeEnum;
import com.quaint.shop.member.helper.SendCodeHelper;
import com.quaint.shop.member.service.UserLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * desc: 用户登录相关 controller
 * </p>
 *
 * @author quaint
 * @since 29 April 2020
 */
@Api(tags = {"用户登录相关接口","分类: 用户"})
@RestController
@RequestMapping("userLogin")
public class UserLoginController {

    @Autowired
    UserLoginService userLoginService;

    @Autowired
    SendCodeHelper sendCodeHelper;


    @ApiOperation("手机&密码登录")
    @PostMapping("phone")
    public UserLogin.Result userLogin(@RequestBody UserLogin.Param param){
        return userLoginService.userLogin(param);
    }

    @ApiOperation("手机登录发送验证码")
    @PostMapping("sendCode")
    public SendCode.Result loginSendCode(@RequestBody SendCode.Param param){
        SendCode.Result result = new SendCode.Result();
        result.setStatus(sendCodeHelper.sendCode(param.getPhone(), SendCodeEnum.LOGIN_TYPE.getValue()));
        return result;
    }



}
