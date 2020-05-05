package com.quaint.shop.spike.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 秒杀订单表
 * </p>
 *
 * @author quaint
 * @since 2020-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("spike_order")
public class SpikeOrderPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 秒杀编号
     */
    @TableField("spike_no")
    private String spikeNo;

    /**
     * 商品编码
     */
    @TableField("product_code")
    private String productCode;

    /**
     * 订单状态 0 未支付 1 已支付 2 已取消
     */
    @TableField("order_status")
    private Integer orderStatus;

    /**
     * 应付金额
     */
    @TableField("should_price")
    private BigDecimal shouldPrice;

    /**
     * 实付金额
     */
    @TableField("pey_price")
    private BigDecimal peyPrice;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 是否有效
     */
    private Boolean valid;


}
