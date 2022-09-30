package com.tweetapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ErrorInfo {

    private String status;
    private String errorCode;
    private String description;

    public ErrorInfo(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.errorCode = errorCode.getCode();
        this.description = errorCode.getDescription();
    }
}
