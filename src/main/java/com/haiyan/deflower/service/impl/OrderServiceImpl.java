package com.haiyan.deflower.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiyan.deflower.dao.OrderDao;
import com.haiyan.deflower.dao.OrderDetailDao;
import com.haiyan.deflower.mapper.OrderDetailMapper;
import com.haiyan.deflower.mapper.OrderMapper;
import com.haiyan.deflower.mapper.OrderStatusMapper;
import com.haiyan.deflower.pojo.*;
import com.haiyan.deflower.service.OrderService;
import com.haiyan.deflower.utils.IdWorker;
import com.haiyan.deflower.utils.ServletUtils;
import com.haiyan.deflower.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author haiyan
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderDao orderDao;
    private final OrderStatusMapper orderStatusMapper;
    private final OrderDetailMapper orderDetailMapper;
    private final OrderDetailDao orderDetailDao;
    private final IdWorker idWorker;

    @Autowired
    private UserUtils userUtils;

    public OrderServiceImpl(OrderMapper orderMapper, OrderDao orderDao, OrderStatusMapper orderStatusMapper, OrderDetailMapper orderDetailMapper, OrderDetailDao orderDetailDao, IdWorker idWorker) {
        this.orderMapper = orderMapper;
        this.orderDao = orderDao;
        this.orderStatusMapper = orderStatusMapper;
        this.orderDetailMapper = orderDetailMapper;
        this.orderDetailDao = orderDetailDao;
        this.idWorker = idWorker;
    }

    @Override
    public Long createOrder(Order order) {
        // 生成orderId
        long orderId = idWorker.nextId();
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        // 初始化数据
        order.setBuyerNick(user.getUsername());
        order.setCreateTime(new Date());
        order.setOrderId(orderId);
        order.setUserId(user.getId());
        // 保存数据
        this.orderMapper.insert(order);

        // 保存订单状态
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(orderId);
        orderStatus.setCreateTime(order.getCreateTime());
        // 初始状态为未付款
        orderStatus.setStatus(1);

        this.orderStatusMapper.insert(orderStatus);

        // 订单详情中添加orderId
        order.getOrderDetails().forEach(od -> {
            od.setOrderId(orderId);
            this.orderDetailMapper.insert(od);
        });
        // 保存订单详情,使用批量插入功能

        log.debug("生成订单，订单编号：{}，用户id：{}", orderId, user.getId());

        return orderId;
    }

    @Override
    public Order queryById(Long id) {
        // 查询订单
        Order order = this.orderMapper.selectById(id);

        // 查询订单详情
        OrderDetail detail = new OrderDetail();
        detail.setOrderId(id);
        List<OrderDetail> details = this.orderDetailDao
                .lambdaQuery()
                .eq(OrderDetail::getOrderId,id)
                .list();
        order.setOrderDetails(details);

        // 查询订单状态
        OrderStatus status = this.orderStatusMapper.selectById(order.getOrderId());
        order.setStatus(status.getStatus());
        return order;
    }

    @Override
    public PageList<Order> queryUserOrderList(Integer page, Integer rows, Integer status) {
        Page<Order> orderPage = orderDao.lambdaQuery()
                .eq(Order::getUserId, userUtils.getUser(ServletUtils.getRequest()).getId())
                .orderByDesc(Order::getCreateTime)
                .page(new Page<>(page, rows));
        return PageList.of(orderPage.getRecords(), orderPage);
    }

    @Override
    public Boolean updateStatus(Long id, Integer status) {
        OrderStatus record = new OrderStatus();
        record.setOrderId(id);
        record.setStatus(status);
        // 根据状态判断要修改的时间
        switch (status) {
            case 2:
                // 付款
                record.setPaymentTime(new Date());
                break;
            case 3:
                // 发货
                record.setConsignTime(new Date());
                break;
            case 4:
                // 确认收获，订单结束
                record.setEndTime(new Date());
                break;
            case 5:
                // 交易失败，订单关闭
                record.setCloseTime(new Date());
                break;
            default:
                return null;
        }
        int count = this.orderStatusMapper.updateById(record);
        return count == 1;
    }
}
