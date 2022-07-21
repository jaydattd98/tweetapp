package com.tweetapp.service;

import com.tweetapp.entity.LoginUserDetails;
import com.tweetapp.entity.UserEntity;
import com.tweetapp.exception.ErrorCode;
import com.tweetapp.exception.TweetAppServiceException;
import com.tweetapp.mapper.UserMapper;
import com.tweetapp.model.request.CreateUserRequest;
import com.tweetapp.model.response.UserResponse;
import com.tweetapp.repository.LoginUserRepository;
import com.tweetapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.tweetapp.mapper.UserMapper.toModel;
import static com.tweetapp.mapper.UserMapper.toUserResponse;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final LoginUserRepository loginUserRepository;

    public UserResponse getUserResponseByUserName(String username) throws TweetAppServiceException {
        return toUserResponse(getUserEntityByUserName(username));
    }

    public UserEntity createUserEntity(CreateUserRequest request) {
        return userRepository.save(toModel(request));
    }

    public UserEntity updateUserEntity(UserEntity user) {
        return userRepository.save(user);
    }

    public UserResponse updateUser(CreateUserRequest userRequest) throws TweetAppServiceException {
        UserEntity user = getUserEntityByUserName(userRequest.getLoginId());
        populateUserDetailsFromRequest(userRequest, user);
        userRepository.save(user);
        return toUserResponse(getUserEntityByUserName(user.getLoginId()));
    }

    public List<UserResponse> getUserResponseList() {
        return userRepository
                .findAll()
                .stream()
                .map(UserMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public List<UserEntity> getUserEntities() {
        return userRepository.findAll();
    }

    public String removeUser(String userName) throws TweetAppServiceException {
        userRepository.delete(getUserEntityByUserName(userName));
        loginUserRepository.delete(new LoginUserDetails(userName));
        return "UserEntity deleted successfully..!!";
    }

    public UserEntity getUserEntityByUserName(String loginId) throws TweetAppServiceException {
        return userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new TweetAppServiceException(ErrorCode.USER_NOT_FOUND));
    }

    /**
     * Methods to check the username is already used or not
     *
     * @param userName
     * @return false for already been used
     */
    public boolean isUserNameAvailable(String userName) {
        return getUserEntities()
                .stream()
                .filter(user -> userName.equalsIgnoreCase(user.getLoginId()))
                .collect(Collectors.toList())
                .isEmpty();
    }

    private void populateUserDetailsFromRequest(CreateUserRequest userRequest, UserEntity user) {
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setContactNumber(userRequest.getContactNumber());
        user.setEmail(userRequest.getEmail());
    }

    public UserResponse forgotPassword(String username, String newPassword) throws TweetAppServiceException {
        UserEntity userEntity = getUserEntityByUserName(username);
        userEntity.setPassword(newPassword);
        return toUserResponse(updateUserEntity(userEntity));
    }
}
