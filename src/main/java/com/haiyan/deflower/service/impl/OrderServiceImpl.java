package com.haiyan.deflower.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiyan.deflower.dao.OrderDao;
import com.haiyan.deflower.dao.OrderDetailDao;
import com.haiyan.deflower.dto.request.OrderBody;
import com.haiyan.deflower.dto.response.OrderDetailsVo;
import com.haiyan.deflower.exception.ExceptionResult;
import com.haiyan.deflower.mapper.OrderDetailMapper;
import com.haiyan.deflower.mapper.OrderMapper;
import com.haiyan.deflower.mapper.OrderStatusMapper;
import com.haiyan.deflower.pojo.*;
import com.haiyan.deflower.service.OrderService;
import com.haiyan.deflower.utils.IdWorker;
import com.haiyan.deflower.utils.ServletUtils;
import com.haiyan.deflower.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    private final ModelMapper modelMapper;

    @Autowired
    private UserUtils userUtils;

    public OrderServiceImpl(OrderMapper orderMapper, OrderDao orderDao, OrderStatusMapper orderStatusMapper, OrderDetailMapper orderDetailMapper, OrderDetailDao orderDetailDao, IdWorker idWorker, ModelMapper modelMapper) {
        this.orderMapper = orderMapper;
        this.orderDao = orderDao;
        this.orderStatusMapper = orderStatusMapper;
        this.orderDetailMapper = orderDetailMapper;
        this.orderDetailDao = orderDetailDao;
        this.idWorker = idWorker;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long createOrder(OrderBody orderBody) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        // 生成orderId
        long orderId = idWorker.nextId();
        // 初始化数据
        Order order = modelMapper.map(orderBody,Order.class);
        order.setBuyerNick(user.getUsername());
        order.setCreateTime(new Date());
        order.setOrderId(String.valueOf(orderId));
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
        orderBody.getOrderDetails().forEach(od -> {
            od.setOrderId(orderId);
            this.orderDetailMapper.insert(od);
        });
        // 保存订单详情,使用批量插入功能

        log.debug("生成订单，订单编号：{}，用户id：{}", orderId, user.getId());

        return orderId;
    }

    @Override
    public OrderDetailsVo queryById(Long id) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        // 查询订单
        Order order = this.orderMapper.selectById(id);
        OrderDetailsVo orderDetailsVo = modelMapper.map(order,OrderDetailsVo.class);
        // 查询订单详情
        OrderDetail detail = new OrderDetail();
        detail.setOrderId(id);
        List<OrderDetail> details = this.orderDetailDao
                .lambdaQuery()
                .eq(OrderDetail::getOrderId,id)
                .list();
        orderDetailsVo.setOrderDetails(details);

        // 查询订单状态
        OrderStatus status = this.orderStatusMapper.selectById(order.getOrderId());
        orderDetailsVo.setStatus(status.getStatus());
        return orderDetailsVo;
    }

    @Override
    public PageList<Order> queryUserOrderList(Integer page, Integer rows, Integer status) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        Page<Order> orderPage = orderDao.lambdaQuery()
                .eq(Order::getUserId, userUtils.getUser(ServletUtils.getRequest()).getId())
                .orderByDesc(Order::getCreateTime)
                .page(new Page<>(page, rows));
        orderPage.getRecords().forEach(order -> {
            // 查询订单状态
            order.setStatus(status);
        });
        return PageList.of(orderPage.getRecords(), orderPage);
    }

    @Override
    public Boolean updateStatus(Long id, Integer status) {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
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

    @Override
    public Integer getOrderCount() {
        // 获取登录用户
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"请先登陆");
        }
        return orderDao.lambdaQuery().eq(Order::getUserId,user.getId()).count();
    }
}
