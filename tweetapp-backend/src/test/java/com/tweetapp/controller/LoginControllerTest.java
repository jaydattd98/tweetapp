package com.tweetapp.controller;

import com.tweetapp.kafka.KafkaProducer;
import com.tweetapp.repository.LoginUserRepository;
import com.tweetapp.repository.UserRepository;
import com.tweetapp.service.LoginService;
import com.tweetapp.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
public class LoginControllerTest {

    public static final String TWEET_APP_URL_TEMPLATE = "/api/v1.0/tweets";
    @Autowired
    private MockMvc mvc;
    @MockBean
    private LoginService loginService;
    @MockBean
    private UserService userService;
    @MockBean
    private LoginUserRepository loginUserRepository;
    @MockBean
    private UserRepository userRepository;

//    @Test
//    public void loginUser() {
//    }
//
//    @Test
//    public void registerUser() {
//    }
//
//    @Test
//    public void forgotPassword() {
//    }
//
//    @Test
//    public void logoutUser() {
//    }
}