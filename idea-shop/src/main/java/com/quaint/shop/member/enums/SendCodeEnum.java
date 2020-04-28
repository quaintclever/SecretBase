package com.quaint.shop.member.enums;

/**
 * <p>
 * desc: 验证码类型枚举
 * </p>
 *
 * @author quaint
 * @since 22 April 2020
 */
public enum SendCodeEnum {

    /**
     * 验证码类型枚举
     *
     */
    NULL_TYPE("","空类型"),

    LOGIN_TYPE("login","登录验证码类型"),
    BIND_PHONE_TYPE("bind","绑定手机验证码类型"),

    ;


    private String value;
    private String desc;

    SendCodeEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
