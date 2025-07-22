package com.leo.springbootmall.controller;

import com.leo.springbootmall.dto.OrderRequest;
import com.leo.springbootmall.dto.OrderResponse;
import com.leo.springbootmall.model.Order;
import com.leo.springbootmall.service.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/users/{userId}/orders")
    public ResponseEntity<OrderResponse> addOrder(@PathVariable @NotNull @Positive Integer userId, @RequestBody @Valid OrderRequest orderRequest) {
        Integer orderId = orderService.addOrder(userId, orderRequest);
        OrderResponse orderResponse = orderService.getOrderById(orderId);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }
}
