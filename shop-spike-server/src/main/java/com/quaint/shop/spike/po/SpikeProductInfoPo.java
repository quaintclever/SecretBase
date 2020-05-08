package com.quaint.shop.spike.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 秒杀商品信息表
 * </p>
 *
 * @author quaint
 * @since 2020-05-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("spike_product_info")
public class SpikeProductInfoPo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 商品编码
     */
    @TableField("product_code")
    private String productCode;

    /**
     * 商品名称
     */
    @TableField("product_name")
    private String productName;

    /**
     * 秒杀价
     */
    @TableField("spike_price")
    private BigDecimal spikePrice;

    /**
     * 划线价
     */
    @TableField("del_line_price")
    private BigDecimal delLinePrice;

    /**
     * 当前商品数量
     */
    @TableField("current_product_num")
    private Integer currentProductNum;

    /**
     * 当前已售数量
     */
    @TableField("current_sold_num")
    private Integer currentSoldNum;

    /**
     * 状态(0.下架 1.上架 2.已售罄)
     */
    private Integer status;

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
