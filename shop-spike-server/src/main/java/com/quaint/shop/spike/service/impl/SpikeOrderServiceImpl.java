package com.quaint.shop.spike.service.impl;

import com.quaint.shop.spike.dao.SpikeOrderMapper;
import com.quaint.shop.spike.dto.SpikeOrderInsert;
import com.quaint.shop.spike.po.SpikeOrderPo;
import com.quaint.shop.spike.service.SpikeOrderService;
import com.quaint.shop.spike.utils.OrderUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * desc: 秒杀订单服务 实现
 * </p>
 *
 * @author quaint
 * @since 05 May 2020
 */
@Service
@Slf4j
public class SpikeOrderServiceImpl implements SpikeOrderService {

    @Autowired
    SpikeOrderMapper spikeOrderMapper;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public SpikeOrderInsert.Result insertSpikeOrder(SpikeOrderInsert.Param param) {

        log.info("【 ===> insertSpikeOrder <=== 】method start, param:[{}]", param);
        SpikeOrderInsert.Result result = new SpikeOrderInsert.Result();

        SpikeOrderPo order = new SpikeOrderPo();
        order.setMemberId(param.getMemberId());
        order.setOrderStatus(0);
        order.setShouldPrice(param.getShouldPrice());
        order.setPayPrice(param.getPayPrice());
        order.setProductCode(param.getProductCode());
        order.setSpikeNo(OrderUtils.generateOrderCode("MS",redisTemplate));
        boolean status = spikeOrderMapper.insert(order) > 0;
        result.setStatus(status);

        log.info("【 ===> insertSpikeOrder <=== 】method end, result:[{}]", result);
        return result;
    }
}