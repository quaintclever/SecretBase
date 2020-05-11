package com.quaint.shop.member.dto.info;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * desc: UserLogin dto
 * </p>
 *
 * @author quaint
 * @since 28 April 2020
 */
@Data
@ApiModel("用户信息dto")
public class MemberInfoDto {

    /**
     * 主键
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 积分
     */
    private BigDecimal integral;

    /**
     * 地址
     */
    private String address;


}
