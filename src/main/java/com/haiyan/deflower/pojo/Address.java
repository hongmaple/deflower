package com.haiyan.deflower.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 收货地址
 * @author haiyan
 */
@Data
@Table(name="tb_address")
public class Address {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户id
     */
    private Long uid;
    /**
     * 收件人
     */
    private String consignee;
    /**
     * 收件人手机
     */
    private String consigneePhone;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String district;
    /**
     * 详细地址
     */
    private String detail;

    public Address() {
    }
}
