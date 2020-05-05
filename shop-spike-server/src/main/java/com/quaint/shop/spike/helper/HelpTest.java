package com.quaint.shop.spike.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author quaint
 * @since 05 May 2020
 */
@SuppressWarnings("all")
public class HelpTest {

//    public static void main(String[] args) {
//
//        //初始化队列
//        DelayQueue<OrderDelayed> queue = new DelayQueue<>();
//
//        LocalDateTime now = LocalDateTime.now();
//
//        OrderDelayed order01 = new OrderDelayed();
//        order01.setOrderNo("DD2019021401");
//        order01.setStatus("待付款");
//        order01.setCreatedTime(now);
//        queue.add(order01);
//        System.out.println(String.format("时间：%s，订单：%s加入队列", order01.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), order01.getOrderNo()));
//
//        OrderDelayed order02 = new OrderDelayed();
//        order02.setOrderNo("DD2019021402");
//        order02.setStatus("待付款");
//        //时间-1小时
//        order02.setCreatedTime(now.minusHours(1));
//        queue.add(order02);
//        System.out.println(String.format("时间：%s，订单：%s加入队列", order02.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), order02.getOrderNo()));
//
//
//        new Thread(new OrderCancelTask(queue,o->{
//           if (o instanceof OrderDelayed){
//               OrderDelayed od = (OrderDelayed) o;
//               od.setStatus("已取消");
//               System.out.println(String.format("时间：%s，订单：%s已过期", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), od.getOrderNo()));
//           }
//        })).start();
//
//        System.out.println("main 线程执行到结尾");
//
//    }

}
