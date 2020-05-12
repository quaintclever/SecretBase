package com.quaint.shop.spike.controller;

import com.quaint.shop.common.annotation.AopLoggerResult;
import com.quaint.shop.member.annotation.AopLogin;
import com.quaint.shop.member.helper.MemberContext;
import com.quaint.shop.spike.dto.SpikeOrderInsert;
import com.quaint.shop.spike.service.SpikeOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * desc: 秒杀订单提交 控制器
 * </p>
 *
 * @author quaint
 * @since 05 May 2020
 */
@Api(tags = {"秒杀订单相关接口","分类: 秒杀"})
@RestController
public class SpikeOrderController {


    @Autowired
    SpikeOrderService spikeOrderService;

    @ApiOperation("秒杀订单下单接口")
    @PostMapping("insert/spike/order")
    @AopLoggerResult
    @AopLogin
    public SpikeOrderInsert.Result insertSpikeOrder(@RequestBody SpikeOrderInsert.Param param){
        Long userId = MemberContext.getMemberId();
        if (userId==null){
            throw new RuntimeException("用户信息获取异常");
        } else{
            return spikeOrderService.insertSpikeOrder(param,userId);
        }
    }

}
