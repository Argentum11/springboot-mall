# Spring Boot mall

This project is part of the **[Java 工程師必備！Spring Boot 零基礎入門](https://hahow.in/courses/5fe22e7fe810e10fc483dd78)** course. It serves as a hands-on practice for learning Spring Boot fundamentals. Some images below belong to the course instructor.

## Database Setup

Run the following SQL to set up the `mall` database and seed it with an example product:

```sql
CREATE DATABASE mall;
USE mall;

CREATE TABLE product
(
    product_id         INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    product_name       VARCHAR(128) NOT NULL,
    category           VARCHAR(32)  NOT NULL,
    image_url          VARCHAR(256) NOT NULL,
    price              INT          NOT NULL,
    stock              INT          NOT NULL,
    description        VARCHAR(1024),
    created_date       TIMESTAMP    NOT NULL,
    last_modified_date TIMESTAMP    NOT NULL
);

INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('蘋果', 'FOOD', 'https://cdn.pixabay.com/photo/2014/02/01/17/28/apple-256261__480.jpg', 20, 10, '這是來自澳洲的蘋果！', '2022-03-01 02:41:28', '2022-03-01 02:41:32');
```

## Features

- products
  - get product
  - add product
  - update product
