package com.leo.springbootmall.dao;

import com.leo.springbootmall.dto.UserRegisterRequest;
import com.leo.springbootmall.model.User;

public interface UserDao {
    Integer addUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);

    User getUserByEmail(String email);
}
