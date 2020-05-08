package com.quaint.shop.spike.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * desc: 秒杀订单插入  dto
 * </p>
 *
 * @author quaint
 * @since 05 May 2020
 */
public interface GetSpikeProduct {

    @Data
    @ApiModel("获取秒杀商品 入参")
    class Param{

    }

    @Data
    @ApiModel("获取秒杀商品 出参")
    class Result{

        @ApiModelProperty("商品信息")
        private List<Info> prodInfos;

    }

    @Data
    @ApiModel("商品信息")
    class Info{

        @ApiModelProperty("商品编码")
        private String productCode;

        @ApiModelProperty("商品名称")
        private String productName;

        @ApiModelProperty("秒杀价")
        private BigDecimal spikePrice;

        @ApiModelProperty("划线价")
        private BigDecimal delLinePrice;

        @ApiModelProperty("当前商品数量")
        private Integer spikeProductNum;

        @ApiModelProperty("当前已售数量")
        private Integer spikeSoldNum;

        @ApiModelProperty("状态(0.预热 1.秒杀中 2.已售罄 3.已结束)")
        private Integer status;

        @ApiModelProperty("秒杀开始时间")
        private LocalDateTime startTime;

        @ApiModelProperty("秒杀结束时间")
        private LocalDateTime endTime;
        
        
    }

}
