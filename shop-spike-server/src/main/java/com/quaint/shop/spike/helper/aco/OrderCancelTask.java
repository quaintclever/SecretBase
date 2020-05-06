package com.quaint.shop.spike.helper.aco;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.function.Consumer;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author quaint
 * @since 05 May 2020
 */
public class OrderCancelTask<T extends Delayed> implements Runnable {

    private DelayQueue queue;

    private Consumer<T> consumer;

    public OrderCancelTask(DelayQueue delayQueue, Consumer<T> consumer) {
        this.queue = delayQueue;
        this.consumer = consumer;
    }

    @Override
    public void run() {

        // 如果 阻塞队列为空, 把该线程放回 单个线程池, 进入等待状态
        while (!queue.isEmpty()) {
            T order = null;
            try {
                order = (T) queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            consumer.accept(order);
        }

    }
}
