package com.quaint.shop.spike;

import com.quaint.shop.spike.helper.CancelOrderHelper;
import com.quaint.shop.spike.po.SpikeOrderPo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * desc: 订单测试类
 * </p>
 *
 * @author quaint
 * @since 06 May 2020
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpikeApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderTest {

    @Autowired
    CancelOrderHelper cancelOrderHelper;

    /**
     * 测试自动取消订单
     */
    @Test
    public void testAutoCancelOrder(){

        LocalDateTime now = LocalDateTime.now();

        SpikeOrderPo order = new SpikeOrderPo();
        order.setId(-1L);
        order.setSpikeNo("MS20200505001");
        order.setOrderStatus(0);
        order.setCreateTime(now);
        cancelOrderHelper.autoCancelOrder(order);


        order = new SpikeOrderPo();
        order.setId(-1L);
        order.setSpikeNo("MS20200505002");
        order.setOrderStatus(0);
        //时间-1小时
        order.setCreateTime(now.minusHours(1));
        cancelOrderHelper.autoCancelOrder(order);

        order = new SpikeOrderPo();
        order.setId(-1L);
        order.setSpikeNo("MS20200505003");
        order.setOrderStatus(0);
        //时间加 5s
        order.setCreateTime(now.plusSeconds(3));
        cancelOrderHelper.autoCancelOrder(order);

        try {
            TimeUnit.SECONDS.sleep(20);
            cancelOrderHelper.autoCancelOrder(order);
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

}
