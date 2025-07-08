package com.leo.springbootmall.controller;

import com.leo.springbootmall.dto.LoginRequest;
import com.leo.springbootmall.dto.UserRegisterRequest;
import com.leo.springbootmall.model.User;
import com.leo.springbootmall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        Integer userId = userService.register(userRegisterRequest);
        User newUser = userService.getUserById(userId);
        if (newUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid LoginRequest loginRequest) {
        User existingUser = userService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(existingUser);
    }
}
