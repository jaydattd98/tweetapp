package com.tweetapp.mapper;

import com.tweetapp.entity.UserEntity;
import com.tweetapp.model.request.CreateUserRequest;
import com.tweetapp.model.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserResponse toUserResponse(UserEntity user) {
        return new UserResponse(user.getFirstName(),user.getLastName(),
                user.getEmail(),user.getLoginId(),user.getContactNumber());
    }

    public static UserEntity toModel(CreateUserRequest request) {
        return new UserEntity(request.getFirstName(),request.getLastName(),request.getEmail(),
                request.getLoginId(),request.getPassword(),request.getContactNumber());
    }
}
