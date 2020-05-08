package com.quaint.shop.spike.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>
 * desc: 秒杀订单插入  dto
 * </p>
 *
 * @author quaint
 * @since 05 May 2020
 */
public interface SpikeOrderInsert {

    @Data
    @ApiModel("秒杀订单插入 入参")
    class Param{

        @ApiModelProperty("商品编码")
        private String productCode;

        @ApiModelProperty("应付金额")
        private BigDecimal shouldPrice;

    }

    @Data
    @ApiModel("秒杀订单插入 出参")
    class Result{

        @ApiModelProperty("是否成功")
        boolean status;

    }

}
