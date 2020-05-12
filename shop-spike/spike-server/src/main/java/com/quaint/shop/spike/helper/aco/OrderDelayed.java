package com.quaint.shop.spike.helper.aco;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * desc: 订单延迟取消
 * </p>
 *
 * @author quaint
 * @since 05 May 2020
 */
@Data
public class OrderDelayed implements Delayed {


    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;


    /**
     * 过期时间（单位为毫秒，这里表示10秒）
     */
    private long expireTime = 10000;

    /**
     * 获取过期的
     * @param unit time
     * @return -1 过期
     */
    @Override
    public long getDelay(@NotNull TimeUnit unit) {
        //消息是否到期（是否可以被读取出来）判断的依据。当返回负数，说明消息已到期，此时消息就可以被读取出来了
        return unit.convert(this.createdTime.toInstant(ZoneOffset.of("+8")).toEpochMilli()
                + expireTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 获取队列中快过期的
     * @param o
     * @return
     */
    @Override
    public int compareTo(@NotNull Delayed o) {
        //这里根据取消时间来比较，如果取消时间小的，就会优先被队列提取出来
        return this.getCreatedTime().compareTo(((OrderDelayed) o).getCreatedTime());
    }
}
