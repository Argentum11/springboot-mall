package com.leo.springbootmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.springbootmall.constant.ProductCategory;
import com.leo.springbootmall.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void getProductSuccess() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/{productId}", 1);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.productName", equalTo("蘋果（澳洲）")))
                .andExpect(jsonPath("$.category", equalTo("FOOD")))
                .andExpect(jsonPath("$.imageUrl", notNullValue()))
                .andExpect(jsonPath("$.price", equalTo(30)))
                .andExpect(jsonPath("$.stock", equalTo(10)))
                .andExpect(jsonPath("$.description", notNullValue()))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));
    }

    @Test
    public void getProductNotFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/{productId}", -1);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }

    @Test
    @Transactional
    public void addProductSuccess() throws Exception {
        Product newProduct = new Product();
        newProduct.setProductName("kiwi");
        newProduct.setCategory(ProductCategory.FOOD);
        newProduct.setImageUrl("www.google.com");
        newProduct.setPrice(10);
        newProduct.setStock(100);
        newProduct.setDescription("a healthy fruit");
        String newProductString = objectMapper.writeValueAsString(newProduct);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newProductString);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.productName", equalTo(newProduct.getProductName())))
                .andExpect(jsonPath("$.category", equalTo(newProduct.getCategory().name())))
                .andExpect(jsonPath("$.imageUrl", equalTo(newProduct.getImageUrl())))
                .andExpect(jsonPath("$.price", equalTo(newProduct.getPrice())))
                .andExpect(jsonPath("$.stock", equalTo(newProduct.getStock())))
                .andExpect(jsonPath("$.description", equalTo(newProduct.getDescription())))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));
    }

    @Test
    @Transactional
    public void addProductIllegalArgument() throws Exception {
        Product newProduct = new Product();
        newProduct.setProductName("kiwi");
        newProduct.setPrice(-1);
        String newProductString = objectMapper.writeValueAsString(newProduct);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newProductString);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    @Transactional
    public void updateProductSuccess() throws Exception {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("banana");
        updatedProduct.setCategory(ProductCategory.FOOD);
        updatedProduct.setImageUrl("www.google.com");
        updatedProduct.setPrice(10);
        updatedProduct.setStock(100);
        updatedProduct.setDescription("a yellow fruit");
        String updatedProductString = objectMapper.writeValueAsString(updatedProduct);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/products/{productId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedProductString);
        ;
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.productName", equalTo(updatedProduct.getProductName())))
                .andExpect(jsonPath("$.category", equalTo(updatedProduct.getCategory().name())))
                .andExpect(jsonPath("$.imageUrl", equalTo(updatedProduct.getImageUrl())))
                .andExpect(jsonPath("$.price", equalTo(updatedProduct.getPrice())))
                .andExpect(jsonPath("$.stock", equalTo(updatedProduct.getStock())))
                .andExpect(jsonPath("$.description", equalTo(updatedProduct.getDescription())))
                .andExpect(jsonPath("$.createdDate", notNullValue()))
                .andExpect(jsonPath("$.lastModifiedDate", notNullValue()));
    }

    @Test
    @Transactional
    public void updateProductInvalidArgument() throws Exception {
        Product updatedProduct = new Product();
        String updatedProductString = objectMapper.writeValueAsString(updatedProduct);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/products/{productId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedProductString);
        ;
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(400));
    }

    @Test
    @Transactional
    public void updateProductNotFound() throws Exception {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("banana");
        updatedProduct.setCategory(ProductCategory.FOOD);
        updatedProduct.setImageUrl("www.google.com");
        updatedProduct.setPrice(10);
        updatedProduct.setStock(100);
        updatedProduct.setDescription("a yellow fruit");
        String updatedProductString = objectMapper.writeValueAsString(updatedProduct);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/products/{productId}", -1).contentType(MediaType.APPLICATION_JSON)
                .content(updatedProductString);
        ;
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }

    @Test
    @Transactional
    public void deleteProductSuccess() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/products/{productId}", 1);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(204));
    }

    @Test
    @Transactional
    public void deleteProductNotFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/products/{productId}", -1);
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(204));
    }

    @Test
    public void getProducts() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products");
        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.page", equalTo(1)))
                .andExpect(jsonPath("$.limit", equalTo(5)))
                .andExpect(jsonPath("$.total", equalTo(7)))
                .andExpect(jsonPath("$.results", hasSize(5)));
    }

    @Test
    public void getProductsFiltering() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products")
                .param("category", "CAR")
                .param("search", "T");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.page", equalTo(1)))
                .andExpect(jsonPath("$.limit", equalTo(5)))
                .andExpect(jsonPath("$.total", equalTo(2)))
                .andExpect(jsonPath("$.results", hasSize(2)));
    }

    @Test
    public void getProductsSorting() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products")
                .param("orderBy", "price")
                .param("ascending", "false");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.page", equalTo(1)))
                .andExpect(jsonPath("$.limit", equalTo(5)))
                .andExpect(jsonPath("$.total", equalTo(7)))
                .andExpect(jsonPath("$.results", hasSize(5)))
                .andExpect(jsonPath("$.results[0].productId", equalTo(6)))
                .andExpect(jsonPath("$.results[1].productId", equalTo(5)))
                .andExpect(jsonPath("$.results[2].productId", equalTo(7)))
                .andExpect(jsonPath("$.results[3].productId", equalTo(4)))
                .andExpect(jsonPath("$.results[4].productId", equalTo(2)));
    }

    @Test
    public void getProductsPagination() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products")
                .param("page", "2")
                .param("limit", "1");

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.page", equalTo(2)))
                .andExpect(jsonPath("$.limit", equalTo(1)))
                .andExpect(jsonPath("$.total", equalTo(7)))
                .andExpect(jsonPath("$.results", hasSize(1)));
    }
}