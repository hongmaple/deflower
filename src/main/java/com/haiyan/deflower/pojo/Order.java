package com.haiyan.deflower.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 订单
 * @author haiyan
 */
@Table(name = "tb_order")
@Data
public class Order {

    @Id
    private Long orderId;

    /**
     * 总金额
     */
    @NotNull
    private Long totalPay;

    /**
     * 实付金额
     */
    @NotNull
    private Long actualPay;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 买家昵称
     */
    private String buyerNick;

    /**
     * 收货人全名
     */
    private String receiver;

    /**
     * 移动电话
     */
    private String receiverMobile;

    /**
     * 省份
     */
    private String receiverState;

    /**
     * 城市
     */
    private String receiverCity;

    /**
     * 区/县
     */
    private String receiverDistrict;

    /**
     * 收货地址，如：xx路xx号
     */
    private String receiverAddress;

    @Transient
    private List<OrderDetail> orderDetails;

    @Transient
    private Integer status;
}
