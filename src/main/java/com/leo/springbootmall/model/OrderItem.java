package com.leo.springbootmall.model;

public class OrderItem {
    private Integer orderItemId;
    private Integer orderId;
    private Integer productId;
    private Integer quantity;
    private Integer itemTotal;

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

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

    public Integer getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(Integer itemTotal) {
        this.itemTotal = itemTotal;
    }
}
