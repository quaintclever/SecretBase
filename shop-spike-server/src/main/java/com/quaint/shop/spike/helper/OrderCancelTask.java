package com.quaint.shop.spike.helper;

import lombok.Data;

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
@Data
public class OrderCancelTask<T extends Delayed> implements Runnable {

    private DelayQueue<T> queue;

    private Consumer<T> consumer;

    public OrderCancelTask(DelayQueue<T> queue, Consumer<T> consumer) {
        this.queue = queue;
        this.consumer = consumer;
    }

    @Override
    public void run() {

        while (!queue.isEmpty()) {
            T order = null;
            try {
                order = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            consumer.accept(order);
        }

    }
}
