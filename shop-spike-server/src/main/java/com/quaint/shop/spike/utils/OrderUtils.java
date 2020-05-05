package com.quaint.shop.spike.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * desc: 秒杀订单提交 控制器
 * </p>
 *
 * @author quaint
 * @since 05 May 2020
 */
public abstract class OrderUtils {


    private volatile static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");


    /**
     * 生成订单号
     *
     * @param type 普通订单 PT 秒杀 MS
     * @return 订单号
     */
    public static String generateOrderCode(String type, RedisTemplate redisTemplate) {

        String dataStr = sdf.format(new Date());
        String key = type + dataStr;
        // 自增
        Long code = redisTemplate.opsForValue().increment(key, 1);
        // 1天以后过期
        redisTemplate.expire(key, 1, TimeUnit.DAYS);
        if (code == 0) {
            code = redisTemplate.opsForValue().increment(key, 1);
        }

        return type + dataStr + StringUtils.leftPad(code.toString(), 4, "0");
    }
}
