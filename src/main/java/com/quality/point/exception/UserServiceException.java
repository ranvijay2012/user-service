package com.quality.point.exception;

import com.quality.point.constant.ErrorDetails;
import lombok.Getter;

@Getter
public class UserServiceException extends Exception{
    private final ErrorDetails errorDetails;
    private final Throwable throwable;

    public UserServiceException(ErrorDetails errorDetails, Throwable throwable){
        super(errorDetails.getErrorMessage());
        this.errorDetails = errorDetails;
        this.throwable = throwable;
    }
}
