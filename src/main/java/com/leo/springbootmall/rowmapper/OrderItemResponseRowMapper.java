package com.leo.springbootmall.rowmapper;

import com.leo.springbootmall.dto.OrderItemResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemResponseRowMapper implements RowMapper<OrderItemResponse> {
    @Override
    public OrderItemResponse mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        OrderItemResponse orderItemResponse = new OrderItemResponse();
        orderItemResponse.setOrderItemId(resultSet.getInt("order_item_id"));
        orderItemResponse.setOrderId(resultSet.getInt("order_id"));
        orderItemResponse.setProductId(resultSet.getInt("product_id"));
        orderItemResponse.setQuantity(resultSet.getInt("quantity"));
        orderItemResponse.setItemTotal(resultSet.getInt("item_total"));
        orderItemResponse.setProductName(resultSet.getString("product_name"));
        orderItemResponse.setImageUrl(resultSet.getString("image_url"));

        return orderItemResponse;
    }
}
