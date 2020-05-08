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
     * @param param code
     * @return
     */
    GetSpikeProduct.Result getSpikeProduct(GetSpikeProduct.Param param);

}
