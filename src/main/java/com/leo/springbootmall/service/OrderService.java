package com.leo.springbootmall.service;

import com.leo.springbootmall.dto.OrderItemDetail;
import com.leo.springbootmall.dto.OrderQueryParams;
import com.leo.springbootmall.dto.OrderRequest;
import com.leo.springbootmall.dto.OrderResponse;

import java.util.List;

public interface OrderService {
    Integer addOrder(Integer userId, OrderRequest orderRequest);

    OrderResponse getOrderById(Integer orderId);

    List<OrderResponse> getOrders(OrderQueryParams orderQueryParams);

    Integer countOrders(OrderQueryParams orderQueryParams);
}
