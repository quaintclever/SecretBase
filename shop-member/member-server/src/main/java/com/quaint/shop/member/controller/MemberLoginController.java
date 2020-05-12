package com.quaint.shop.member.controller;

import com.quaint.shop.common.annotation.AopLoggerResult;
import com.quaint.shop.member.dto.login.MemberLogin;
import com.quaint.shop.member.dto.login.SendCode;
import com.quaint.shop.member.enums.SendCodeEnum;
import com.quaint.shop.member.helper.SendCodeHelper;
import com.quaint.shop.member.server.MemberLoginService;
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
@RequestMapping
public class MemberLoginController {

    @Autowired
    MemberLoginService memberLoginService;

    @Autowired
    SendCodeHelper sendCodeHelper;


    @ApiOperation("手机&密码登录")
    @AopLoggerResult
    @PostMapping("userLogin/phone")
    public MemberLogin.Result userLogin(@RequestBody MemberLogin.Param param){
        return memberLoginService.memberLogin(param);
    }

    @ApiOperation("手机登录发送验证码")
    @AopLoggerResult
    @PostMapping("userLogin/sendCode")
    public SendCode.Result loginSendCode(@RequestBody SendCode.Param param){
        SendCode.Result result = new SendCode.Result();
        result.setStatus(sendCodeHelper.sendCode(param.getPhone(), SendCodeEnum.LOGIN_TYPE.getValue()));
        return result;
    }



}
