package com.tweetapp.controller;

import com.tweetapp.exception.ErrorCode;
import com.tweetapp.exception.TweetAppServiceException;
import com.tweetapp.model.response.UserResponse;
import com.tweetapp.service.LoginService;
import com.tweetapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/tweets")
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    @GetMapping("/users/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.getUserResponseList(), HttpStatus.OK);
    }

    @GetMapping("/user/search/{username}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String username) throws TweetAppServiceException {
        validateUserName(username);
        return new ResponseEntity<>(userService.getUserResponseByUserName(username), HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> removeUser(@PathVariable String username) throws TweetAppServiceException {
        loginService.isUserLoggedIn(username);
        validateUserName(username);
        return new ResponseEntity<>(userService.removeUser(username), HttpStatus.OK);
    }

    private void validateUserName(String username) throws TweetAppServiceException {
        if (isEmpty(username)) {
            throw new TweetAppServiceException(ErrorCode.INVALID_INPUT);
        }
    }

}
