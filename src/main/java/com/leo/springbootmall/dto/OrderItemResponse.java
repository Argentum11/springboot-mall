package com.leo.springbootmall.dto;

import com.leo.springbootmall.model.OrderItem;

public class OrderItemResponse extends OrderItem {
    private String productName;
    private String imageUrl;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
