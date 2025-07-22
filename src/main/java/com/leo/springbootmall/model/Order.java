package com.leo.springbootmall.model;

import java.util.Date;

public class Order {
    private Integer orderId;
    private Integer userId;
    private Integer orderTotal;
    private Date createdDate;
    private Date lastModifiedDate;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Integer getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Integer orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
