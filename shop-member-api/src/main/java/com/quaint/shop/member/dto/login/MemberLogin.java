package com.quaint.shop.member.dto.login;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * <p>
 * desc: MemberLogin dto
 * </p>
 *
 * @author quaint
 * @since 28 April 2020
 */
public interface MemberLogin {

    @Data
    @ApiModel("用户登录入参")
    class Param{

        private String phone;
        private String password;

    }

    @Data
    @ApiModel("用户登录出参")
    class Result{

        private String token;

    }

}
