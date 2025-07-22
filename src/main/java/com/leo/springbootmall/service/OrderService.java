package com.leo.springbootmall.service;

import com.leo.springbootmall.dto.OrderRequest;
import com.leo.springbootmall.dto.OrderResponse;

public interface OrderService {
    Integer addOrder(Integer userId, OrderRequest orderRequest);

    OrderResponse getOrderById(Integer orderId);
}
