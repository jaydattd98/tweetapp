package com.tweetapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TweetAppServiceException extends Exception {
    private ErrorCode errorCode;
}
