package com.quaint.shop.spike.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quaint.shop.spike.po.SpikeOrderPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 秒杀订单表 Mapper 接口
 * </p>
 *
 * @author quaint
 * @since 2020-05-05
 */
@Mapper
public interface SpikeOrderMapper extends BaseMapper<SpikeOrderPo> {

}
