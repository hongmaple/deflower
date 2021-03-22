package com.haiyan.deflower.service;

import com.haiyan.deflower.pojo.Order;
import com.haiyan.deflower.pojo.PageList;

/**
 * @author haiyan
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    Long createOrder(Order order);

    /**
     * 根据订单id查询订单
     *
     * @param id
     * @return
     */
    Order queryById(Long id);

    /**
     * 分页查询登录用户订单
     *
     * @param page
     * @param rows
     * @param status
     * @return
     */
    PageList<Order> queryUserOrderList(Integer page, Integer rows, Integer status);

    /**
     * 更新订单状态
     *
     * @param id
     * @param status
     * @return
     */
    Boolean updateStatus(Long id, Integer status);

    /**
     * 获取订单数
     * @return 订单数
     */
    Integer getOrderCount();

}
