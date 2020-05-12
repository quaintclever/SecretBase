package com.quaint.shop.member.helper;

import com.quaint.shop.member.enums.SendCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author quaint
 * @since 28 April 2020
 */
@Component
@Slf4j
public class SendCodeHelper {

    /**
     * 发送验证码 缓存前缀
     */
    private static final String CODE_PREFIX = "code_";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * 兼容旧版本 不带类型的验证码 签名为空 会默认使用 一个签名
     *
     * @param phone 手机号
     * @return status
     */
    public boolean sendCode(String phone) {
        return this.sendCode(phone, SendCodeEnum.NULL_TYPE.getValue());
    }


    /**
     * 缓存不同类型的验证码
     * 获取缓存
     * cacheService.get(SMS_CODE_PREFIX+ type +phone)
     *
     * @param phone 手机号
     * @param type  缓存类型标识
     * @return status
     */
    public boolean sendCode(String phone, String type) {

        String code = "" + RandomUtils.nextInt(0, 9)
                + RandomUtils.nextInt(0, 9)
                + RandomUtils.nextInt(0, 9)
                + RandomUtils.nextInt(0, 9);

        // 假设 验证码 发送成功了
        log.info("[验证码获取处] ==> [{}]", code);

        // 设置redis 缓存
        redisTemplate.opsForValue().set(CODE_PREFIX + type + phone, code);
        return true;
    }

}
