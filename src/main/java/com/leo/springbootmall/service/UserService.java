package com.leo.springbootmall.service;

import com.leo.springbootmall.dto.UserRegisterRequest;
import com.leo.springbootmall.model.User;

public interface UserService {
    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
