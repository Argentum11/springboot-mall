package com.leo.springbootmall.dao.impl;

import com.leo.springbootmall.dao.ProductDao;
import com.leo.springbootmall.model.Product;
import com.leo.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Product getProductById(Integer productId) {
        String searchSql = "SELECT product_id, product_name, category, image_url, price, stock, description, created_date, " +
                "last_modified_date FROM product WHERE product_id=:productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = namedParameterJdbcTemplate.query(searchSql, map, new ProductRowMapper());
        if (productList.isEmpty()) {
            return null;
        } else {
            return productList.get(0);
        }
    }
}
