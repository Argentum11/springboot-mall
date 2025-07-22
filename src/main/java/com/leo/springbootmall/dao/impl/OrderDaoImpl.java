package com.leo.springbootmall.dao.impl;

import com.leo.springbootmall.dao.OrderDao;
import com.leo.springbootmall.dto.OrderItemDetail;
import com.leo.springbootmall.model.Order;
import com.leo.springbootmall.model.OrderItem;
import com.leo.springbootmall.rowmapper.OrderItemDetailRowMapper;
import com.leo.springbootmall.rowmapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer addOrder(Integer userId, Integer orderTotal) {
        String insertSql = "INSERT INTO orders (user_id, order_total, created_date, last_modified_date) VALUES (:user_id, :order_total, :created_date, :last_modified_date)";
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("order_total", orderTotal);
        Date now = new Date();
        map.put("created_date", now);
        map.put("last_modified_date", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(insertSql, new MapSqlParameterSource(map), keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Order getOrderById(Integer orderId) {
        String searchSql = "SELECT order_id, user_id, order_total, created_date, last_modified_date FROM orders WHERE order_id=:order_id";
        Map<String, Object> map = new HashMap<>();
        map.put("order_id", orderId);


        List<Order> orderList = namedParameterJdbcTemplate.query(searchSql, map, new OrderRowMapper());
        if (orderList.isEmpty()) {
            return null;
        } else {
            return orderList.get(0);
        }
    }

    @Override
    public void addOrderItems(Integer orderId, List<OrderItem> orderItems) {
        String insertSql = "INSERT INTO order_item (order_id, product_id, quantity, item_total) VALUES (:order_id, :product_id, :quantity, :item_total)";

        MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItems.size()];
        for (int i = 0; i < orderItems.size(); i++) {
            OrderItem orderItem = orderItems.get(i);
            parameterSources[i] = new MapSqlParameterSource();
            parameterSources[i].addValue("order_id", orderId);
            parameterSources[i].addValue("product_id", orderItem.getProductId());
            parameterSources[i].addValue("quantity", orderItem.getQuantity());
            parameterSources[i].addValue("item_total", orderItem.getItemTotal());
        }

        namedParameterJdbcTemplate.batchUpdate(insertSql, parameterSources);
    }

    @Override
    public List<OrderItemDetail> getOrderItemDetailsByOrderId(Integer orderId) {
        String searchSql = "SELECT oi.order_item_id, oi.order_id, oi.product_id, oi.quantity, oi.item_total, p.product_name, p.image_url " +
                "FROM order_item as oi " +
                "LEFT JOIN product as p ON oi.product_id = p.product_id " +
                "WHERE oi.order_id=:order_id";

        Map<String, Object> map = new HashMap<>();
        map.put("order_id", orderId);
        return namedParameterJdbcTemplate.query(searchSql, map, new OrderItemDetailRowMapper());
    }
}
