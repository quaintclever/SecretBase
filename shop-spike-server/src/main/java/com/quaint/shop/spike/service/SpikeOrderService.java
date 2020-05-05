package com.quaint.shop.spike.service;

import com.quaint.shop.spike.dto.SpikeOrderInsert;

/**
 * <p>
 * desc: 秒杀订单服务
 * </p>
 *
 * @author quaint
 * @since 03 May 2020
 */
public interface SpikeOrderService {

    /**
     * 秒杀订单插入
     * @param param param
     * @return bool
     */
    SpikeOrderInsert.Result insertSpikeOrder(SpikeOrderInsert.Param param);

}
