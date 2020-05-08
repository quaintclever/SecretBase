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
     * 秒杀商品配置数量
     */
    @TableField("spike_product_num")
    private Integer spikeProductNum;

    /**
     * 当前秒杀商品已售数量
     */
    @TableField("spike_sold_num")
    private Integer spikeSoldNum;

    /**
     * 状态(0.预热 1.秒杀中 2.已售罄 3.已结束)
     */
    private Integer status;

    /**
     * 秒杀开始时间
     */
    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * 秒杀结束时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;

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
