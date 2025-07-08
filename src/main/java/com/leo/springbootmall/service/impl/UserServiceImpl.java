package com.leo.springbootmall.service.impl;

import com.leo.springbootmall.dao.UserDao;
import com.leo.springbootmall.dto.LoginRequest;
import com.leo.springbootmall.dto.UserRegisterRequest;
import com.leo.springbootmall.model.User;
import com.leo.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private final Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        // the reason for different names in service layer and DAO layer is:
        //     Service layer validates business logic while DAO performs data operation
        User existingUser = userDao.getUserByEmail(userRegisterRequest.getEmail());
        if (existingUser != null) {
            logger.warn("email {} has been registered", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        userRegisterRequest.setPassword(encoder.encode(userRegisterRequest.getPassword()));

        return userDao.addUser(userRegisterRequest);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User login(LoginRequest loginRequest) {
        User existingUser = userDao.getUserByEmail(loginRequest.getEmail());
        if (existingUser == null) {
            logger.warn("email {} hasn't registered", loginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        if (!encoder.matches(loginRequest.getPassword(), existingUser.getPassword())) {
            logger.warn("email {} password mismatch", loginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return existingUser;

    }
}
