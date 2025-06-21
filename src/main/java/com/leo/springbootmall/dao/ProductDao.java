package com.leo.springbootmall.dao;

import com.leo.springbootmall.dto.ProductRequest;
import com.leo.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer addProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);
}
