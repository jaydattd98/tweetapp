package com.tweetapp.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Class UserResponse
 *
 * This DTA model used to give proper UI response to User
 * And Avoid populating sensitive details to user like "password,DOB .etc" in response
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String loginId;
    private String contactNumber;
}
