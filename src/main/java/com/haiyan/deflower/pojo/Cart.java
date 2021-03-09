package com.haiyan.deflower.pojo;

import lombok.Data;

import javax.persistence.Table;

/**
 * 购物车
 * @author haiyan
 */
@Data
@Table(name="tb_cart")
public class Cart {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 商品id
     */
    private Long skuId;
    /**
     * 标题
     */
    private String title;
    /**
     * 图片
     */
    private String image;
    /**
     * 加入购物车时的价格
     */
    private Long price;
    /**
     * 购买数量
     */
    private Integer num;
}