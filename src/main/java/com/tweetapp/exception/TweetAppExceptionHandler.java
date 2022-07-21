package com.tweetapp.exception;

import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TweetAppExceptionHandler {

    @ExceptionHandler(TweetAppServiceException.class)
    public ResponseEntity<ErrorInfo> handelAuthServiceException(TweetAppServiceException e) {
        return new ResponseEntity<>(new ErrorInfo(e.getErrorCode()), getHttpStatus(e.getErrorCode().getStatus()));
    }

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<ErrorInfo> handelInternalException(InternalException e) {
        return new ResponseEntity<>(new ErrorInfo(ErrorCode.INTERNAL_SERVER_EXCEPTION), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private HttpStatus getHttpStatus(String status) {
        switch (status) {
            case "400":
                return HttpStatus.BAD_REQUEST;
            case "401":
                return HttpStatus.UNAUTHORIZED;
            case "403":
                return HttpStatus.FORBIDDEN;
            case "404":
                return HttpStatus.NOT_FOUND;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
