package com.tweetapp.service;

import com.tweetapp.entity.LoginUserDetails;
import com.tweetapp.entity.UserEntity;
import com.tweetapp.exception.ErrorCode;
import com.tweetapp.exception.TweetAppServiceException;
import com.tweetapp.model.request.LoginRequest;
import com.tweetapp.model.response.UserResponse;
import com.tweetapp.repository.LoginUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.tweetapp.mapper.UserMapper.toUserResponse;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserService userService;
    private final LoginUserRepository loginUserRepository;

    private static boolean checkIsUserAvailable(UserEntity user, LoginRequest request) {
        return request.getUsername().equalsIgnoreCase(user.getLoginId())
                && request.getPassword().equals(user.getPassword());
    }

    public UserResponse login(LoginRequest request) throws TweetAppServiceException {
        UserEntity userEntity = userService
                .getUserEntities()
                .stream()
                .filter(user -> checkIsUserAvailable(user, request))
                .findFirst()
                .orElseThrow(() -> new TweetAppServiceException(ErrorCode.INVALID_USER_NAME_AND_PASSWORD));
        log.info("Found User details from DB => Output user :"+userEntity.toString());
        // Storing login user in db
        LoginUserDetails userDetails = loginUserRepository.findByUserName(userEntity.getLoginId()).orElse(null);
        if (userDetails == null) {
            LoginUserDetails loginDetails = new LoginUserDetails();
            loginDetails.setUserName(userEntity.getLoginId());
            loginUserRepository.save(loginDetails);
        }
        return toUserResponse(userEntity);
    }

    public void isUserLoggedIn(String username) throws TweetAppServiceException {
        loginUserRepository.findByUserName(username)
                .orElseThrow(() -> new TweetAppServiceException(ErrorCode.UNAUTHORIZED));
        log.info("User is logged in username :"+username);
    }

    public String logout(String username) throws TweetAppServiceException {
        loginUserRepository.delete(loginUserRepository.findByUserName(username)
                .orElseThrow(() -> new TweetAppServiceException(ErrorCode.UNAUTHORIZED)));
        return "Logout Successfully ...!!";
    }
}
