package com.quaint.shop.spike.helper;

import com.quaint.shop.spike.dao.SpikeOrderMapper;
import com.quaint.shop.spike.helper.aco.DelayQueueProvider;
import com.quaint.shop.spike.helper.aco.OrderCancelTask;
import com.quaint.shop.spike.helper.aco.OrderDelayed;
import com.quaint.shop.spike.po.SpikeOrderPo;
import com.quaint.shop.spike.utils.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;

/**
 * <p>
 * desc: 取消订单帮助类
 * </p>
 *
 * @author quaint
 * @since 06 May 2020
 */
@Component
@Slf4j
public class CancelOrderHelper {

    @Autowired
    SpikeOrderMapper spikeOrderMapper;

    /**
     * 10s 自动取消订单
     */
    @Async
    public void autoCancelOrder(SpikeOrderPo order){

        DelayQueue delayQueue = DelayQueueProvider.getSingleDelayQueue();

        OrderDelayed delayedOrder = new OrderDelayed();
        delayedOrder.setOrderId(order.getId());
        delayedOrder.setCreatedTime(order.getCreateTime());
        delayQueue.add(delayedOrder);

        ExecutorService singleThread = ThreadPoolUtils.getSingleExecutorService();

        // 单个线程一直监控  不需要 shutdown
        singleThread.submit(new OrderCancelTask<OrderDelayed>(delayQueue, orderCancelCheck -> {
            SpikeOrderPo spikeOrderPo = spikeOrderMapper.selectById(orderCancelCheck.getOrderId());
            if (spikeOrderPo != null && spikeOrderPo.getOrderStatus() == 0){
                // 订单取消
                SpikeOrderPo updateStatus = new SpikeOrderPo();
                updateStatus.setId(spikeOrderPo.getId());
                updateStatus.setOrderStatus(2);
                spikeOrderMapper.updateById(updateStatus);
                log.info("订单：[{}]已被取消", spikeOrderPo.getSpikeNo());
            } else {
                log.info("该订单状态已经修改过了");
            }
        }));

    }


}
