package com.quaint.shop.member.controller;

import com.quaint.shop.common.annotation.AopLogger;
import com.quaint.shop.common.annotation.AopLoggerResult;
import com.quaint.shop.member.annotation.AopLogin;
import com.quaint.shop.member.constant.MemberApiUrlConstants;
import com.quaint.shop.member.dto.info.MemberInfoDto;
import com.quaint.shop.member.helper.MemberContext;
import com.quaint.shop.member.server.MemberInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class MemberInfoController {

    @Autowired
    MemberInfoService memberInfoService;

    @ApiOperation("获取当前登录用户的信息")
    @PostMapping("memberInfo/current")
    @AopLogin
    @AopLoggerResult
    public MemberInfoDto getMemberInfo(){
        Long userId = MemberContext.getMemberId();
        return memberInfoService.getMemberInfoById(userId);
    }

    @ApiOperation("根据id获取用户的信息")
    @AopLogger
    @PostMapping(MemberApiUrlConstants.GET_MEMBER_INFO_BY_ID)
    public MemberInfoDto getMemberInfoById(@RequestBody Long id){
        return memberInfoService.getMemberInfoById(id);
    }

}

