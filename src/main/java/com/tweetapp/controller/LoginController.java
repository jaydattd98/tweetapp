package com.tweetapp.controller;

import com.tweetapp.entity.UserEntity;
import com.tweetapp.exception.ErrorCode;
import com.tweetapp.exception.TweetAppServiceException;
import com.tweetapp.model.request.CreateUserRequest;
import com.tweetapp.model.request.LoginRequest;
import com.tweetapp.model.response.UserResponse;
import com.tweetapp.service.LoginService;
import com.tweetapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/tweets")
public class LoginController {

    private final LoginService loginService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserResponse> loginUser(@RequestBody LoginRequest request) throws TweetAppServiceException {
        validateUserRequestInput(request);
        return new ResponseEntity<>(loginService.login(request), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody CreateUserRequest request) throws TweetAppServiceException {
        validateCreateUserRequestInput(request);
        return new ResponseEntity<>(userService.createUserEntity(request), HttpStatus.CREATED);
    }

    @PostMapping("/{username}/forgot")
    public ResponseEntity<UserResponse> forgotPassword(@PathVariable String username, @RequestBody String newPassword) throws TweetAppServiceException {
        validateUserName(username);
        return new ResponseEntity<>(userService.forgotPassword(username, newPassword), HttpStatus.OK);
    }

    @GetMapping("/{username}/logout")
    public ResponseEntity<String> logoutUser(@PathVariable String username) throws TweetAppServiceException {
        validateUserName(username);
        return new ResponseEntity<>(loginService.logout(username), HttpStatus.OK);
    }


    private void validateUserName(String username) throws TweetAppServiceException {
        if (isEmpty(username)) {
            throw new TweetAppServiceException(ErrorCode.INVALID_INPUT);
        }
    }

    private void validateCreateUserRequestInput(CreateUserRequest user) throws TweetAppServiceException {
        if (user == null || isEmpty(user.getFirstName()) || isEmpty(user.getEmail())
                || isEmpty(user.getLoginId()) || isEmpty(user.getPassword())
                || isEmpty(user.getContactNumber())) {
            throw new TweetAppServiceException(ErrorCode.INVALID_INPUT);
        }
        if (!userService.isUserNameAvailable(user.getLoginId())) {
            throw new TweetAppServiceException(ErrorCode.USER_NAME_NOT_AVAILABLE);
        }
    }

    private void validateUserRequestInput(LoginRequest request) throws TweetAppServiceException {
        if (request == null || isEmpty(request.getUsername()) || isEmpty(request.getPassword())) {
            throw new TweetAppServiceException(ErrorCode.INVALID_INPUT);
        }
    }

}
