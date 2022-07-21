package com.tweetapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Class LoginRequest
 *
 * This DTA model used to take login param form user
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class LoginRequest {
    private String username;
    private String password;
}
