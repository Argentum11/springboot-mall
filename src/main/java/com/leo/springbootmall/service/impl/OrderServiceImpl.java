package com.leo.springbootmall.service.impl;

import com.leo.springbootmall.dao.OrderDao;
import com.leo.springbootmall.dao.ProductDao;
import com.leo.springbootmall.dao.UserDao;
import com.leo.springbootmall.dto.OrderItemDetail;
import com.leo.springbootmall.dto.OrderItemRequest;
import com.leo.springbootmall.dto.OrderRequest;
import com.leo.springbootmall.dto.OrderResponse;
import com.leo.springbootmall.model.Order;
import com.leo.springbootmall.model.OrderItem;
import com.leo.springbootmall.model.Product;
import com.leo.springbootmall.model.User;
import com.leo.springbootmall.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;

    @Transactional
    @Override
    public Integer addOrder(Integer userId, OrderRequest orderRequest) {
        validateUser(userId);

        // Process each OrderItemRequest: validate product, check and update stock, calculate totals
        int orderTotal = 0;
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest orderItemRequest : orderRequest.getOrderItemRequests()) {
            // check if product exists
            Product product = productDao.getProductById(orderItemRequest.getProductId());
            if (product == null) {
                logger.warn("Product {} doesn't exist", orderItemRequest.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            // check if product has sufficient stock
            if (orderItemRequest.getQuantity() > product.getStock()) {
                logger.warn("Product {} doesn't have enough stock. Current stock: {}, requested quantity {}.", orderItemRequest.getProductId(), product.getStock(), orderItemRequest.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            productDao.updateStock(orderItemRequest.getProductId(), product.getStock() - orderItemRequest.getQuantity());

            // calculate order total
            int itemTotal = orderItemRequest.getQuantity() * product.getPrice();
            orderTotal += itemTotal;

            // convert OrderItemRequest to OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(orderItemRequest.getProductId());
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setItemTotal(itemTotal);
            orderItems.add(orderItem);
        }

        Integer orderId = orderDao.addOrder(userId, orderTotal);
        orderDao.addOrderItems(orderId, orderItems);
        return orderId;
    }

    private void validateUser(Integer userId) {
        User user = userDao.getUserById(userId);
        if (user == null) {
            logger.warn("User {} doesn't exist.", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public OrderResponse getOrderById(Integer orderId) {
        OrderResponse orderResponse = new OrderResponse();
        Order order = orderDao.getOrderById(orderId);
        orderResponse.setOrderId(order.getOrderId());
        orderResponse.setUserId(order.getUserId());
        orderResponse.setOrderTotal(order.getOrderTotal());
        orderResponse.setCreatedDate(order.getCreatedDate());
        orderResponse.setLastModifiedDate(order.getLastModifiedDate());

        List<OrderItemDetail> orderItemDetails = orderDao.getOrderItemDetailsByOrderId(orderId);
        orderResponse.setOrderItemDetails(orderItemDetails);
        return orderResponse;
    }
}
