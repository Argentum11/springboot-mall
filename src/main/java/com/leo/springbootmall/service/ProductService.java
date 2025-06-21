package com.leo.springbootmall.service;

import com.leo.springbootmall.dto.ProductRequest;
import com.leo.springbootmall.model.Product;
import org.springframework.data.relational.core.sql.In;

public interface ProductService {
    Product getProductById(Integer productId);

    Integer addProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);
}
