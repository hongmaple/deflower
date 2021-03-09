package com.haiyan.deflower.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 订单详情
 * @author haiyan
 */
@Table(name = "tb_order_detail")
@Data
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 商品购买数量
     */
    private Integer num;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品单价
     */
    private Long price;

    /**
     * 图片
     */
    private String image;
}
