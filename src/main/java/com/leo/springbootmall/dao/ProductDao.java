package com.leo.springbootmall.dao;

import com.leo.springbootmall.dto.ProductQueryParams;
import com.leo.springbootmall.dto.ProductRequest;
import com.leo.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProducts(ProductQueryParams productQueryParams);

    Integer countProduct(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer addProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void updateStock(Integer productId, Integer newStock);

    void deleteProductById(Integer productId);
}
