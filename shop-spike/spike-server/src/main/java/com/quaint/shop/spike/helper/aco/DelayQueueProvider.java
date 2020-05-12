package com.quaint.shop.spike.helper.aco;

import java.io.Serializable;
import java.util.concurrent.DelayQueue;

/**
 * <p>
 * desc: 延迟队列提供者
 * </p>
 *
 * @author quaint
 * @since 06 May 2020
 */
public class DelayQueueProvider implements Serializable {


    private static volatile DelayQueueProvider instance;

    private DelayQueue delayQueue;

    private static DelayQueueProvider getInstance(){
        if (instance == null){
            synchronized (DelayQueueProvider.class){
                if (instance == null){
                    instance = new DelayQueueProvider();
                }
            }
        }
        return instance;
    }

    /**
     * 获取 单利 延迟队列
     * @return queue
     */
    public static DelayQueue getSingleDelayQueue(){
        return getInstance().delayQueue;
    }


    private DelayQueueProvider() {
        this.delayQueue = new DelayQueue<>();
    }
}
