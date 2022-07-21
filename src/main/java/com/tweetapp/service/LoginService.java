package com.tweetapp.service;

import com.tweetapp.entity.LoginUserDetails;
import com.tweetapp.entity.UserEntity;
import com.tweetapp.exception.ErrorCode;
import com.tweetapp.exception.TweetAppServiceException;
import com.tweetapp.model.request.LoginRequest;
import com.tweetapp.model.response.UserResponse;
import com.tweetapp.repository.LoginUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.tweetapp.mapper.UserMapper.toUserResponse;

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

        // Storing login user in db
        loginUserRepository.save(new LoginUserDetails(userEntity.getLoginId()));
        return toUserResponse(userEntity);
    }

    public void isUserLoggedIn(String username) throws TweetAppServiceException {
        loginUserRepository.findById(username)
                .orElseThrow(() -> new TweetAppServiceException(ErrorCode.UNAUTHORIZED));
    }

    public String logout(String username) throws TweetAppServiceException {
        loginUserRepository.delete(loginUserRepository.findById(username)
                .orElseThrow(() -> new TweetAppServiceException(ErrorCode.UNAUTHORIZED)));
        return "Logout Successfully ...!!";
    }
}
