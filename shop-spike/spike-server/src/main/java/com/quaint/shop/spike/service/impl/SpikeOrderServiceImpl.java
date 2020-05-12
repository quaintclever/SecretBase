package com.quaint.shop.spike.service.impl;

import com.quaint.shop.member.api.MemberInfoApi;
import com.quaint.shop.member.dto.info.MemberInfoDto;
import com.quaint.shop.spike.dao.SpikeOrderMapper;
import com.quaint.shop.spike.dto.SpikeOrderInsert;
import com.quaint.shop.spike.helper.aco.CancelOrderHelper;
import com.quaint.shop.spike.po.SpikeOrderPo;
import com.quaint.shop.spike.service.SpikeOrderService;
import com.quaint.shop.spike.utils.OrderUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Autowired
    CancelOrderHelper cancelOrderHelper;

    @Autowired
    MemberInfoApi memberInfoApi;

    @Override
    public SpikeOrderInsert.Result insertSpikeOrder(SpikeOrderInsert.Param param, Long memberId) {

        log.info("【 ===> insertSpikeOrder <=== 】method start, param:[{}]", param);
        SpikeOrderInsert.Result result = new SpikeOrderInsert.Result();

        MemberInfoDto memberInfoDto = memberInfoApi.getMemberInfoById(memberId);

        SpikeOrderPo order = new SpikeOrderPo();
        order.setMemberId(memberInfoDto.getId());
        order.setOrderStatus(0);
        order.setShouldPrice(param.getShouldPrice());
        order.setPayPrice(BigDecimal.ZERO);
        order.setProductCode(param.getProductCode());
        order.setSpikeNo(OrderUtils.generateOrderCode("MS",redisTemplate));
        order.setCreateTime(LocalDateTime.now());
        boolean status = spikeOrderMapper.insert(order) > 0;
        result.setStatus(status);

        // 测试, 延迟队列 自动取消订单
        cancelOrderHelper.autoCancelOrder(order);

        log.info("【 ===> insertSpikeOrder <=== 】method end, result:[{}]", result);
        return result;
    }



}
