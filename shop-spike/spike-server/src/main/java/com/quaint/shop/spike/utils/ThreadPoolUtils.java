package com.quaint.shop.spike.utils;

import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * desc: 线程池工具类
 * </p>
 *
 * @author quaint
 * @since 05 May 2020
 */
@SuppressWarnings("all")
public abstract class ThreadPoolUtils {

    private static volatile ExecutorService executorService;

    private static volatile ExecutorService singleExecutorService;

    /**
     * 获取线程池
     * @return
     */
    public static ExecutorService getExecutorService(){
        if(executorService == null) {
            synchronized (ThreadPoolUtils.class){
                if(executorService == null){
                    executorService = new ThreadPoolExecutor(
                            5,
                            10,
                            0,
                            TimeUnit.SECONDS,
                            new LinkedBlockingQueue<>(1024),
                            new CustomizableThreadFactory("shop-thread-pool"),
                            new ThreadPoolExecutor.CallerRunsPolicy());
                }
            }
        }
        return executorService;
    }

    /**
     * 获取单个线程池
     * 拒绝策略: 直接丢弃 队列中的任务
     * @return
     */
    public static ExecutorService getSingleExecutorService(){
        if(singleExecutorService == null) {
            synchronized (ThreadPoolUtils.class){
                if(singleExecutorService == null){
//                    singleExecutorService = Executors.newSingleThreadExecutor(new CustomizableThreadFactory("shop-thread-pool-single-"));
                    singleExecutorService = new ThreadPoolExecutor(1, 1,
                            0L, TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<>(1),
                            new CustomizableThreadFactory("shop-thread-pool-single-"),
                            // 直接丢弃 多余的任务
                            new ThreadPoolExecutor.DiscardPolicy());
                }
            }
        }
        return singleExecutorService;
    }

    /**
     * 提交一个任务处理(交给线程池处理)
     * @param task
     */
    public static void submit(Runnable task){
        ExecutorService executorService = getExecutorService();
        executorService.submit(task);
    }

}
