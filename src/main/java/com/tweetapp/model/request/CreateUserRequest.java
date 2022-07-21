package com.tweetapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Class CreateUserRequest
 *
 * This DTO model is used to take only required param from user to register
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String loginId;
    private String password;
    private String contactNumber;
}
