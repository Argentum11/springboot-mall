package com.leo.springbootmall.dao.impl;

import com.leo.springbootmall.dao.UserDao;
import com.leo.springbootmall.dto.UserRegisterRequest;
import com.leo.springbootmall.model.User;
import com.leo.springbootmall.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Integer addUser(UserRegisterRequest userRegisterRequest) {
        String addSql = "INSERT INTO users(email, password, created_date, last_modified_date) VALUES (:email, :password, :created_date, :last_modified_date)";
        Map<String, Object> map = new HashMap<>();
        map.put("email", userRegisterRequest.getEmail());
        map.put("password", userRegisterRequest.getPassword());

        Date now = new Date();
        map.put("created_date", now);
        map.put("last_modified_date", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        Integer userId = namedParameterJdbcTemplate.update(addSql, new MapSqlParameterSource(map), keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public User getUserById(Integer userId) {
        String searchSql = "SELECT user_id, email, password, created_date, last_modified_date FROM users WHERE user_id=:user_id";
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);

        List<User> userList = namedParameterJdbcTemplate.query(searchSql, map, new UserRowMapper());
        if (userList.isEmpty()) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        String searchSql = "SELECT user_id, email, password, created_date, last_modified_date FROM users WHERE email=:email";
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        List<User> userList = namedParameterJdbcTemplate.query(searchSql, map, new UserRowMapper());
        if (userList.isEmpty()) {
            return null;
        } else {
            return userList.get(0);
        }
    }
}
