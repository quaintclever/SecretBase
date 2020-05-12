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
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    public GetSpikeProduct.Result getSpikeProductList() {
        log.info("【 ===> getSpikeProductList <=== 】method start");
        GetSpikeProduct.Result result = new GetSpikeProduct.Result();

        List<GetSpikeProduct.Info> spikeProduct = spikeProductInfoMapper.getSpikeProduct(null);
        result.setProdInfos(spikeProduct);

        log.info("【 ===> getSpikeProductList <=== 】method end, result:[{}]", result);
        return result;
    }

    @Override
    public Boolean refreshSpikeProductCache() {
        // 查询秒杀的商品信息
        List<GetSpikeProduct.Info> spikeProduct = spikeProductInfoMapper.getSpikeProduct(null);
        return this.updateProductStatus(spikeProduct);
    }

    /**
     * 更新商品状态 并设置缓存
     * @param infos 商品信息
     * @return bool
     */
    private boolean updateProductStatus(List<GetSpikeProduct.Info> infos) {

        if (CollectionUtils.isEmpty(infos)) {
            log.info("没有可以设置的秒杀商品");
            return false;
        }

        Map<String, Integer> updateMap = new HashMap<>(infos.size());
        // 把秒杀商品放入到缓存中  code -> entity
        infos.forEach(sp -> {
            Integer status = this.getSpikeProductStatus(sp);
            if (status != null) {
                sp.setStatus(status);
                updateMap.put(sp.getProductCode(), status);
            }
            redisTemplate.opsForValue().set(SpikeBizConstant.SPIKE_PRODUCT_CACHE_PRE + sp.getProductCode(), sp);
        });

        if (!CollectionUtils.isEmpty(updateMap)) {
            spikeProductInfoMapper.updateSpikeProductStatus(updateMap);
        }
        return true;
    }

    /**
     * 获取当前商品的状态
     * @param sp 秒杀商品
     * @return null 无需修改 (0.预热 1.秒杀中 2.已售罄 3.已结束)
     */
    private Integer getSpikeProductStatus(GetSpikeProduct.Info sp) {
        LocalDateTime now = LocalDateTime.now();
        // 秒杀开始前... 状态应该为 预热
        if (now.isBefore(sp.getStartTime())) {
            if (sp.getStatus() != 0) {
                return 0;
            }
        }
        // 秒杀进行中... 状态应该为 秒杀中 or 已售馨
        else if (now.isAfter(sp.getStartTime()) && now.isBefore(sp.getEndTime())) {
            if (sp.getSpikeProductNum() >= sp.getSpikeSoldNum() && sp.getStatus() != 1) {
                return 1;
            }
            if (sp.getSpikeProductNum() <= sp.getSpikeSoldNum() && sp.getStatus() != 2) {
                return 2;
            }
        }
        // 秒杀结束.. 状态应该为 秒杀已结束
        else {
            if (sp.getStatus() != 3) {
                return 3;
            }
        }
        return null;
    }

    @Override
    public GetSpikeProduct.Info getSpikeProductByCode(String code) {

        // 查询缓存
        Object o = redisTemplate.opsForValue().get(SpikeBizConstant.SPIKE_PRODUCT_CACHE_PRE + code);
        if (o instanceof GetSpikeProduct.Info) {
            GetSpikeProduct.Info info = (GetSpikeProduct.Info) o;
            Integer status = this.getSpikeProductStatus(info);
            // 如果为空, 证明不需要修改, 否则,证明缓存无效, 需要重新加载缓存
            if (status == null){
                return info;
            } else {
                // 大批缓存失效时 增加随机睡眠时间, 减少数据库的并发访问
                try {
                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // 没有缓存 更新信息 并插入缓存
        List<GetSpikeProduct.Info> spikeProduct = spikeProductInfoMapper.getSpikeProduct(Collections.singletonList(code));
        if (CollectionUtils.isEmpty(spikeProduct)) {
            return null;
        } else {
            this.updateProductStatus(spikeProduct);
            return spikeProduct.get(0);
        }
    }
}
