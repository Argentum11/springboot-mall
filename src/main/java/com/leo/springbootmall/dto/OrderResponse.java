package com.leo.springbootmall.dto;

import com.leo.springbootmall.model.Order;
import com.leo.springbootmall.model.OrderItem;

import java.util.List;

public class OrderResponse extends Order {

    private List<OrderItemResponse> orderItemResponses;

    public List<OrderItemResponse> getOrderItemResponses() {
        return orderItemResponses;
    }

    public void setOrderItemResponses(List<OrderItemResponse> orderItemResponses) {
        this.orderItemResponses = orderItemResponses;
    }
}
