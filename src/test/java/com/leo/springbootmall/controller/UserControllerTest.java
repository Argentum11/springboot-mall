package com.leo.springbootmall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leo.springbootmall.dao.UserDao;
import com.leo.springbootmall.dto.UserRegisterRequest;
import com.leo.springbootmall.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserDao userDao;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @Transactional
    void registerSuccess() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test@gmail.com");
        userRegisterRequest.setPassword("123");
        String userRegisterRequestString = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userRegisterRequestString);

        mockMvc.perform(requestBuilder).andDo(print())
                .andExpect(status().is(201))
                .andExpect(jsonPath("userId", notNullValue()))
                .andExpect(jsonPath("email", equalTo(userRegisterRequest.getEmail())))
                .andExpect(jsonPath("createdDate", notNullValue()))
                .andExpect(jsonPath("lastModifiedDate", notNullValue()));

        // check if the password is hashed
        User registeredUser = userDao.getUserByEmail(userRegisterRequest.getEmail());
        assertNotEquals(userRegisterRequest.getPassword(), registeredUser.getPassword());
    }

    @Test
    @Transactional
    void registerInvalidEmail() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("hello");
        userRegisterRequest.setPassword("123");
        String userRegisterRequestString = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userRegisterRequestString);

        mockMvc.perform(requestBuilder).andDo(print())
                .andExpect(status().is(400));
    }

    @Test
    @Transactional
    void registerEmailExists() throws Exception {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("kimi@gmail.com");
        userRegisterRequest.setPassword("kimi");
        String userRegisterRequestString = objectMapper.writeValueAsString(userRegisterRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userRegisterRequestString);

        mockMvc.perform(requestBuilder).andDo(print())
                .andExpect(status().is(201));

        mockMvc.perform(requestBuilder).andDo(print())
                .andExpect(status().is(400));
    }
}