package com.quaint.shop.spike.service;

import com.quaint.shop.spike.dto.GetSpikeProduct;

/**
 * <p>
 * desc: 秒杀商品信息服务
 * </p>
 *
 * @author quaint
 * @since 03 May 2020
 */
public interface SpikeProductInfoService {


    /**
     * 获取秒杀商品信息
     * @return
     */
    GetSpikeProduct.Result getSpikeProduct();

    /**
     * 把秒杀的商品信息 提前加入到 redis 缓存中
     * @return
     */
    Boolean putSpikeProduct2Cache();

}
