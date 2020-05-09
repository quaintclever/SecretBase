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
     * @return infos
     */
    GetSpikeProduct.Result getSpikeProductList();

    /**
     * 把秒杀的商品信息 提前加入到 redis 缓存中
     * @return bool
     */
    Boolean putSpikeProduct2Cache();

    /**
     * 通过秒杀商品code 获取秒杀商品信息
     * @return 商品信息
     */
    GetSpikeProduct.Info getSpikeProductByCode(String code);

}
