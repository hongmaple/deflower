package com.haiyan.deflower.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.beans.Transient;
import java.util.Date;
import java.util.List;

/**
 * 订单
 * @author haiyan
 */
@TableName("tb_order")
@Data
public class Order {

    @TableId(type = IdType.INPUT)
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

    private List<OrderDetail> orderDetails;

    private Integer status;
}
