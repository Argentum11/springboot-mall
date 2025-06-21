package com.leo.springbootmall.controller;

import com.leo.springbootmall.dto.ProductRequest;
import com.leo.springbootmall.model.Product;
import com.leo.springbootmall.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
        System.out.println(productId);
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.addProduct(productRequest);
        Product newProduct = productService.getProductById(productId);
        if (newProduct != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
