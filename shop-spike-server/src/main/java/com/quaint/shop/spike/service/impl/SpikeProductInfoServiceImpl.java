package com.quaint.shop.spike.service.impl;

import com.quaint.shop.spike.constants.SpikeBizConstant;
import com.quaint.shop.spike.dao.SpikeProductInfoMapper;
import com.quaint.shop.spike.dto.GetSpikeProduct;
import com.quaint.shop.spike.service.SpikeProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public GetSpikeProduct.Result getSpikeProduct() {
        log.info("【 ===> getSpikeProduct <=== 】method start");
        GetSpikeProduct.Result result = new GetSpikeProduct.Result();

        List<GetSpikeProduct.Info> spikeProduct = spikeProductInfoMapper.getSpikeProduct(null);
        result.setProdInfos(spikeProduct);

        log.info("【 ===> getSpikeProduct <=== 】method end, result:[{}]", result);
        return result;
    }

    @Override
    public Boolean putSpikeProduct2Cache() {

        // 查询秒杀的商品信息
        List<GetSpikeProduct.Info> spikeProduct = spikeProductInfoMapper.getSpikeProduct(null);
        if (CollectionUtils.isEmpty(spikeProduct)) {
            log.info("没有可以设置的秒杀商品");
            return false;
        }

        Map<String,Integer> updateMap = new HashMap<>(spikeProduct.size());
        LocalDateTime now = LocalDateTime.now();
        // 把秒杀商品放入到缓存中  code -> entity
        // 状态(0.预热 1.秒杀中 2.已售罄 3.已结束)
        spikeProduct.forEach(sp -> {
            // 秒杀开始前... 状态应该为 预热
            if (now.isBefore(sp.getStartTime())){
                if (sp.getStatus() != 0){
                    sp.setStatus(0);
                    updateMap.put(sp.getProductCode(),0);
                }
            }
            // 秒杀进行中... 状态应该为 秒杀中 or 已售馨
            else if(now.isAfter(sp.getStartTime()) && now.isBefore(sp.getEndTime())){
                if (sp.getSpikeProductNum() >= sp.getSpikeSoldNum() && sp.getStatus() != 1){
                    sp.setStatus(1);
                    updateMap.put(sp.getProductCode(),1);
                }
                if (sp.getSpikeProductNum() <= sp.getSpikeSoldNum() && sp.getStatus() != 2){
                    sp.setStatus(2);
                    updateMap.put(sp.getProductCode(),2);
                }
            }
            // 秒杀结束.. 状态应该为 秒杀已结束
            else {
                if (sp.getStatus() != 3){
                    sp.setStatus(3);
                    updateMap.put(sp.getProductCode(),3);
                }
            }
            redisTemplate.opsForValue().set(SpikeBizConstant.SPIKE_PRODUCT_CACHE_PRE + sp.getProductCode(), sp);
        });

        if (!CollectionUtils.isEmpty(updateMap)){
            spikeProductInfoMapper.updateSpikeProductStatus(updateMap);
        }

        return true;
    }
}
