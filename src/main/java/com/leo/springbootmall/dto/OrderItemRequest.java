package com.leo.springbootmall.dto;

import jakarta.validation.constraints.Positive;

public class OrderItemRequest {
    @Positive
    private Integer productId;

    @Positive
    private Integer quantity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
