package com.leo.springbootmall.controller;

import com.leo.springbootmall.dto.OrderItemDetail;
import com.leo.springbootmall.dto.OrderQueryParams;
import com.leo.springbootmall.dto.OrderRequest;
import com.leo.springbootmall.dto.OrderResponse;
import com.leo.springbootmall.service.OrderService;
import com.leo.springbootmall.util.PagedResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<PagedResult<OrderResponse>> getOrder(@PathVariable @NotNull @Positive Integer userId,
                                                               @RequestParam String orderBy,
                                                               @RequestParam Boolean ascending,
                                                               @RequestParam(defaultValue = "1") @Min(0) Integer page,
                                                               @RequestParam(defaultValue = "10") @Min(0) @Max(1000) Integer limit) {
        OrderQueryParams orderQueryParams = new OrderQueryParams();
        orderQueryParams.setUserId(userId);
        orderQueryParams.setOrderBy(orderBy);
        orderQueryParams.setAscending(ascending);
        orderQueryParams.setPage(page);
        orderQueryParams.setLimit(limit);

        PagedResult<OrderResponse> pagedResult = new PagedResult<>();
        pagedResult.setTotal(orderService.countOrders(orderQueryParams));
        pagedResult.setLimit(limit);
        pagedResult.setPage(page);
        pagedResult.setResults(orderService.getOrders(orderQueryParams));
        return ResponseEntity.status(HttpStatus.OK).body(pagedResult);
    }
}
