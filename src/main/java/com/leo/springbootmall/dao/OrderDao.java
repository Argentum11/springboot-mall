package com.leo.springbootmall.dao;

import com.leo.springbootmall.dto.OrderItemResponse;
import com.leo.springbootmall.model.Order;
import com.leo.springbootmall.model.OrderItem;

import java.util.List;

public interface OrderDao {
    Integer addOrder(Integer userId, Integer orderTotal);

    Order getOrderById(Integer orderId);

    void addOrderItems(Integer orderId, List<OrderItem> orderItems);

    List<OrderItemResponse> getOrderItemResponsesByOrderId(Integer orderId);
}
