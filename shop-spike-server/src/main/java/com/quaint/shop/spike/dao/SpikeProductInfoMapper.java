package com.quaint.shop.spike.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quaint.shop.spike.dto.GetSpikeProduct;
import com.quaint.shop.spike.po.SpikeProductInfoPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 秒杀商品信息表 Mapper 接口
 * </p>
 *
 * @author quaint
 * @since 2020-05-03
 */
@Mapper
public interface SpikeProductInfoMapper extends BaseMapper<SpikeProductInfoPo> {

    /**
     * 根据多个商品code 查询 秒杀商品详情
     * @param codes productCodes
     * @return infos
     */
    List<GetSpikeProduct.Info> getSpikeProduct(@Param("codes") List<String> codes);

}
