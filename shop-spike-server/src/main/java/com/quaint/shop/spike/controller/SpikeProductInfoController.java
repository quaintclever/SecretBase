package com.quaint.shop.spike.controller;

import com.quaint.shop.common.annotation.AopLoggerResult;
import com.quaint.shop.spike.dto.GetSpikeProduct;
import com.quaint.shop.spike.service.SpikeProductInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * desc:
 * </p>
 *
 * @author quaint
 * @since 08 May 2020
 */
@Api(tags = {"秒杀商品信息相关接口","分类: 秒杀"})
@RestController
public class SpikeProductInfoController {

    @Autowired
    SpikeProductInfoService spikeProductInfoService;

    @ApiOperation("获取秒杀商品信息")
    @PostMapping("get/spike/product")
    @AopLoggerResult
    public GetSpikeProduct.Result getSpikeProduct(@RequestBody GetSpikeProduct.Param param){
        return spikeProductInfoService.getSpikeProduct(param);
    }

}
