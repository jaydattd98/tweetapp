package com.tweetapp.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {

    INVALID_INPUT("400", "J001", "Provided input are not valid, please check the input"),
    USER_NOT_FOUND("404", "J003", "No User found for your request"),
    TWEET_NOT_FOUND("404", "J003", "No Tweet found for your request"),
    REPLY_NOT_FOUND("404", "J003", "No Reply found for your request"),
    USER_NAME_NOT_FOUND_EXCEPTION("404", "J005", "User credential are not matching, no data found in DB"),
    INTERNAL_SERVER_EXCEPTION("500", "J006", "Something went wrong while authenticating user details,Please try again"),
    USER_NAME_NOT_AVAILABLE("401", "J007", "Given user name is already been used, Please choose another"),
    UNAUTHORIZED("401", "J007", "Given user is not logged in, Please login first"),
    EMAIL_ID_ALREADY_REGISTERED("404", "J007", "Given Emil id is already been registered"),
    INVALID_USER_NAME_AND_PASSWORD("401", "J008", "Invalid Login Credential ..!!");

    private String status;
    private String code;
    private String description;

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

}
