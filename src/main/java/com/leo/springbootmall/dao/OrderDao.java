package com.leo.springbootmall.dao;

import com.leo.springbootmall.dto.OrderItemDetail;
import com.leo.springbootmall.dto.OrderQueryParams;
import com.leo.springbootmall.model.Order;
import com.leo.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer addOrder(Integer userId, Integer orderTotal);

    Order getOrderById(Integer orderId);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Integer countOrders(OrderQueryParams orderQueryParams);

    void addOrderItems(Integer orderId, List<OrderItem> orderItems);

    List<OrderItemDetail> getOrderItemDetailsByOrderId(Integer orderId);
}
