package com.leo.springbootmall.controller;

import com.leo.springbootmall.constant.ProductCategory;
import com.leo.springbootmall.dto.ProductQueryParams;
import com.leo.springbootmall.dto.ProductRequest;
import com.leo.springbootmall.model.Product;
import com.leo.springbootmall.service.ProductService;
import com.leo.springbootmall.util.PagedResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<PagedResult<Product>> getProducts(
            // filtering
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            // sorting
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "false") Boolean ascending,
            // pagination
            @RequestParam(defaultValue = "1") @Min(1) Integer page,
            @RequestParam(defaultValue = "5") @Max(100) @Min(0) Integer limit) {
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setAscending(ascending);
        productQueryParams.setPage(page);
        productQueryParams.setLimit(limit);

        List<Product> productList = productService.getProducts(productQueryParams);
        Integer total = productService.countProduct(productQueryParams);

        PagedResult<Product> pagedResult = new PagedResult<>();
        pagedResult.setPage(page);
        pagedResult.setLimit(limit);
        pagedResult.setTotal(total);
        pagedResult.setResults(productList);

        return ResponseEntity.status(HttpStatus.OK).body(pagedResult);
    }

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

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody @Valid ProductRequest productRequest) {
        // verify if the product exists
        Product originProduct = productService.getProductById(productId);
        if (originProduct == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // update product
        productService.updateProduct(productId, productRequest);

        // return update result
        Product updatedProduct = productService.getProductById(productId);
        if (updatedProduct != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        // we don't need to check if product exists because DELETE should succeed if the resource ends up not existing.

        // delete product
        productService.deleteProductById(productId);

        // return delete result
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("product deleted");

    }
}
