package com.leo.springbootmall.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class OrderRequest {
    @NotEmpty
    @Valid
    private List<OrderItemRequest> orderItemRequests;

    public List<OrderItemRequest> getOrderItemRequests() {
        return orderItemRequests;
    }

    public void setOrderItemRequests(List<OrderItemRequest> orderItemRequests) {
        this.orderItemRequests = orderItemRequests;
    }
}
