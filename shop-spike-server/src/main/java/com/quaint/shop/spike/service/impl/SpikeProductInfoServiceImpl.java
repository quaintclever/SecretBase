package com.quaint.shop.spike.service.impl;

import com.quaint.shop.spike.dao.SpikeProductInfoMapper;
import com.quaint.shop.spike.dto.GetSpikeProduct;
import com.quaint.shop.spike.service.SpikeProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * desc: 秒杀商品信息服务实现
 * </p>
 *
 * @author quaint
 * @since 03 May 2020
 */
@Service
@Slf4j
public class SpikeProductInfoServiceImpl implements SpikeProductInfoService {

    @Autowired
    SpikeProductInfoMapper spikeProductInfoMapper;

    @Override
    public GetSpikeProduct.Result getSpikeProduct(GetSpikeProduct.Param param) {
        log.info("【 ===> getSpikeProduct <=== 】method start, param:[{}]", param);
        GetSpikeProduct.Result result = new GetSpikeProduct.Result();

        List<GetSpikeProduct.Info> spikeProduct;
        if (param.isGetAll()){
            spikeProduct = spikeProductInfoMapper.getSpikeProduct(null);
        } else {
            spikeProduct = spikeProductInfoMapper.getSpikeProduct(param.getProductCodes());
        }
        result.setProdInfos(spikeProduct);

        log.info("【 ===> getSpikeProduct <=== 】method end, result:[{}]", result);
        return result;
    }
}
