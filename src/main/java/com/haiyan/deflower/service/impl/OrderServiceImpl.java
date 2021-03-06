package com.haiyan.deflower.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haiyan.deflower.dao.OrderDao;
import com.haiyan.deflower.dao.OrderDetailDao;
import com.haiyan.deflower.dto.request.OrderBody;
import com.haiyan.deflower.dto.request.OrderQuery;
import com.haiyan.deflower.dto.response.OrderDetailsVo;
import com.haiyan.deflower.dto.response.OrderRowVo;
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
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
        // ??????????????????
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"????????????");
        }
        // ??????orderId
        long orderId = idWorker.nextId();
        // ???????????????
        Order order = modelMapper.map(orderBody,Order.class);
        order.setBuyerNick(user.getUsername());
        order.setCreateTime(new Date());
        order.setOrderId(String.valueOf(orderId));
        order.setUserId(user.getId());
        order.setStatus(1);
        // ????????????
        this.orderMapper.insert(order);

        // ??????????????????
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderId(String.valueOf(orderId));
        orderStatus.setCreateTime(order.getCreateTime());
        // ????????????????????????
        orderStatus.setStatus(1);

        this.orderStatusMapper.insert(orderStatus);

        // ?????????????????????orderId
        orderBody.getOrderDetails().forEach(od -> {
            od.setOrderId(String.valueOf(orderId));
            this.orderDetailMapper.insert(od);
        });
        // ??????????????????,????????????????????????

        log.debug("??????????????????????????????{}?????????id???{}", orderId, user.getId());

        return orderId;
    }

    @Override
    public OrderDetailsVo queryById(String id) {
        // ??????????????????
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"????????????");
        }
        // ????????????
        Order order = this.orderMapper.selectById(id);
        OrderDetailsVo orderDetailsVo = modelMapper.map(order,OrderDetailsVo.class);
        // ??????????????????
        OrderDetail detail = new OrderDetail();
        detail.setOrderId(id);
        List<OrderDetail> details = this.orderDetailDao
                .lambdaQuery()
                .eq(OrderDetail::getOrderId,id)
                .list();
        orderDetailsVo.setOrderDetails(details);

        // ??????????????????
        OrderStatus status = this.orderStatusMapper.selectById(order.getOrderId());
        orderDetailsVo.setStatus(status.getStatus());
        return orderDetailsVo;
    }

    @Override
    public PageList<OrderRowVo> queryUserOrderList(Integer page, Integer rows, Integer status) {
        // ??????????????????
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"????????????");
        }
        LambdaQueryChainWrapper<Order> lambdaQuery = orderDao.lambdaQuery();
        if (status>0) {
            lambdaQuery.eq(Order::getStatus,status);
        }
        Page<Order> orderPage = lambdaQuery
                .eq(Order::getUserId, userUtils.getUser(ServletUtils.getRequest()).getId())
                .orderByDesc(Order::getCreateTime)
                .page(new Page<>(page, rows));
        List<OrderRowVo> orderRowVos = new ArrayList<>();
        orderPage.getRecords().forEach(order -> {
            // ??????????????????
            OrderRowVo orderRowVo = modelMapper.map(order,OrderRowVo.class);
            List<OrderDetail> details = this.orderDetailDao
                    .lambdaQuery()
                    .eq(OrderDetail::getOrderId,order.getOrderId())
                    .list();
            orderRowVo.setOrderDetails(details);
            orderRowVos.add(orderRowVo);
        });
        return PageList.of(orderRowVos, orderPage);
    }

    @Override
    public PageList<OrderRowVo> queryOrderList(OrderQuery query) {
        // ??????????????????
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"????????????");
        }
        LambdaQueryChainWrapper<Order> lambdaQuery = orderDao.lambdaQuery();
        if (StringUtils.hasText(query.getOrderId())) {
            lambdaQuery.like(Order::getOrderId,query.getOrderId());
        }
        if (query.getStatus()!=null&&query.getStatus()>0) {
            lambdaQuery.eq(Order::getStatus,query.getStatus());
        }
        Page<Order> orderPage = lambdaQuery
                .orderByDesc(Order::getCreateTime)
                .page(new Page<>(query.getPageNum(), query.getPageSize()));
        List<OrderRowVo> orderRowVos = new ArrayList<>();
        orderPage.getRecords().forEach(order -> {
            // ??????????????????
            OrderRowVo orderRowVo = modelMapper.map(order,OrderRowVo.class);
            List<OrderDetail> details = this.orderDetailDao
                    .lambdaQuery()
                    .eq(OrderDetail::getOrderId,order.getOrderId())
                    .list();
            orderRowVo.setOrderDetails(details);
            orderRowVos.add(orderRowVo);
        });
        return PageList.of(orderRowVos, orderPage);
    }

    @Override
    public Boolean updateStatus(String id, Integer status) {
        // ??????????????????
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"????????????");
        }
        OrderStatus record = new OrderStatus();
        record.setOrderId(id);
        record.setStatus(status);
        // ????????????????????????????????????
        switch (status) {
            case 2:
                // ??????
                record.setPaymentTime(new Date());
                break;
            case 3:
                // ??????
                record.setConsignTime(new Date());
                break;
            case 4:
                // ???????????????????????????
                record.setEndTime(new Date());
                break;
            case 5:
                // ???????????????????????????
                record.setCloseTime(new Date());
                break;
            default:
                return null;
        }
        int count = this.orderStatusMapper.updateById(record);
        Order Order = new Order();
        Order.setOrderId(id);
        Order.setStatus(status);
        orderMapper.updateById(Order);
        return count == 1;
    }

    @Override
    public Integer getOrderCount() {
        // ??????????????????
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"????????????");
        }
        return orderDao.lambdaQuery().eq(Order::getUserId,user.getId()).count();
    }

    @Override
    public Boolean deletedOrder(String id) {
        // ??????????????????
        User user = userUtils.getUser(ServletUtils.getRequest());
        if (Objects.isNull(user)) {
            throw new ExceptionResult("user","false",null,"????????????");
        }
        return orderDao.removeById(id);
    }
}
