package com.quaint.shop.member.dto.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * desc: SendCode dto
 * </p>
 *
 * @author quaint
 * @since 28 April 2020
 */
public interface SendCode {

    @Data
    @ApiModel("入参")
    class Param{

        @ApiModelProperty("发送验证码的手机号")
        private String phone;

    }

    @Data
    @ApiModel("出参")
    class Result{

        @ApiModelProperty("是否成功")
        private boolean status;

    }

}
