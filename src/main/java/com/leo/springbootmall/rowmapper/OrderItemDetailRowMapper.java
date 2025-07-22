package com.leo.springbootmall.rowmapper;

import com.leo.springbootmall.dto.OrderItemDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemDetailRowMapper implements RowMapper<OrderItemDetail> {
    @Override
    public OrderItemDetail mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        OrderItemDetail orderItemDetail = new OrderItemDetail();
        orderItemDetail.setOrderItemId(resultSet.getInt("order_item_id"));
        orderItemDetail.setOrderId(resultSet.getInt("order_id"));
        orderItemDetail.setProductId(resultSet.getInt("product_id"));
        orderItemDetail.setQuantity(resultSet.getInt("quantity"));
        orderItemDetail.setItemTotal(resultSet.getInt("item_total"));
        orderItemDetail.setProductName(resultSet.getString("product_name"));
        orderItemDetail.setImageUrl(resultSet.getString("image_url"));

        return orderItemDetail;
    }
}
