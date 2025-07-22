package com.leo.springbootmall.dto;

import com.leo.springbootmall.model.Order;

import java.util.List;

public class OrderResponse extends Order {

    private List<OrderItemDetail> orderItemDetails;

    public List<OrderItemDetail> getOrderItemDetails() {
        return orderItemDetails;
    }

    public void setOrderItemDetails(List<OrderItemDetail> orderItemDetails) {
        this.orderItemDetails = orderItemDetails;
    }
}
