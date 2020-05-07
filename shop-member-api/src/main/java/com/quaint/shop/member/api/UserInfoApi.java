package com.quaint.shop.member.api;

import com.quaint.shop.member.constant.UserApiUrlConstants;
import com.quaint.shop.member.dto.info.UserInfoDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * desc: 远程调用 userInfo api
 *
 * api 请勿使用 AopLoggerResult 注解
 * 否则会出现 getWriter() has already been called for this response 异常
 * api 如果需要日志 请使用 AopLogger 注解
 * </p>
 *
 * @author quaint
 * @since 29 April 2020
 */
public interface UserInfoApi {

    /**
     * 通过id 查询用户信息
     * @param id id
     * @return dto
     */
    @PostMapping(UserApiUrlConstants.GET_USER_INFO_BY_ID)
    UserInfoDto getUserInfoById(@RequestBody Long id);

}
