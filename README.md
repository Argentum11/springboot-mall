# Spring Boot mall

This project is part of the **[Java 工程師必備！Spring Boot 零基礎入門](https://hahow.in/courses/5fe22e7fe810e10fc483dd78)** course. It serves as a hands-on practice for learning Spring Boot fundamentals. Some images below belong to the course instructor.

## Database Setup

Run the following SQL to set up the `mall` database and product, user table:

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

INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('蘋果（澳洲）', 'FOOD', 'https://cdn.pixabay.com/photo/2016/11/30/15/00/apples-1872997_1280.jpg', 30, 10, '這是來自澳洲的蘋果！', '2022-03-19 17:00:00', '2022-03-22 18:00:00');
INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('蘋果（日本北海道）', 'FOOD', 'https://cdn.pixabay.com/photo/2017/09/26/13/42/apple-2788662_1280.jpg', 300, 5, '這是來自日本北海道的蘋果！', '2022-03-19 18:30:00', '2022-03-19 18:30:00');
INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('好吃又鮮甜的蘋果橘子', 'FOOD', 'https://cdn.pixabay.com/photo/2021/07/30/04/17/orange-6508617_1280.jpg', 10, 50, null, '2022-03-20 09:00:00', '2022-03-24 15:00:00');
INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('Toyota', 'CAR', 'https://cdn.pixabay.com/photo/2014/05/18/19/13/toyota-347288_1280.jpg', 100000, 5, null, '2022-03-20 09:20:00', '2022-03-20 09:20:00');
INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('BMW', 'CAR', 'https://cdn.pixabay.com/photo/2018/02/21/03/15/bmw-m4-3169357_1280.jpg', 500000, 3, '渦輪增壓，直列4缸，DOHC雙凸輪軸，16氣門', '2022-03-20 12:30:00', '2022-03-20 12:30:00');
INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('Benz', 'CAR', 'https://cdn.pixabay.com/photo/2017/03/27/14/56/auto-2179220_1280.jpg', 600000, 2, null, '2022-03-21 20:10:00', '2022-03-22 10:50:00');
INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) VALUES ('Tesla', 'CAR', 'https://cdn.pixabay.com/photo/2021/01/15/16/49/tesla-5919764_1280.jpg', 450000, 5, '世界最暢銷的充電式汽車', '2022-03-21 23:30:00', '2022-03-21 23:30:00');

CREATE TABLE users
(
  user_id            INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
  email              VARCHAR(256) NOT NULL UNIQUE KEY,
  password           VARCHAR(256) NOT NULL,
  created_date       TIMESTAMP    NOT NULL,
  last_modified_date TIMESTAMP    NOT NULL
);

CREATE TABLE orders
(
  order_id           INT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_id            INT       NOT NULL,
  order_total        INT       NOT NULL,
  created_date       TIMESTAMP NOT NULL,
  last_modified_date TIMESTAMP NOT NULL
);

CREATE TABLE order_item
(
  order_item_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  order_id      INT NOT NULL,
  product_id    INT NOT NULL,
  quantity      INT NOT NULL,
  item_total    INT NOT NULL
);

INSERT INTO orders (user_id, order_total, created_date, last_modified_date) VALUES (1, 100110, '2022-06-02 16:51:49', '2022-06-02 16:51:49');
INSERT INTO order_item (order_id, product_id, quantity, item_total) VALUES (1, 1, 2, 60);
INSERT INTO order_item (order_id, product_id, quantity, item_total) VALUES (1, 3, 5, 50);
INSERT INTO order_item (order_id, product_id, quantity, item_total) VALUES (1, 4, 1, 100000);
```

## Features

- products
  - get product
  - add product
  - update product
  - delete product
  - fetch products
    - filtering
      - category
      - name
    - sorting
      - sort by column
      - ascending/descending order
    - pagination
      - page
      - limit
      - total
      - result list
- users
  - register new user
    - validate email uniqueness, format(@Email)
    - exclude password (@JsonIgnore)
  - login
- orders
  - add order
  - get orders
    - sorting
    - pagination
