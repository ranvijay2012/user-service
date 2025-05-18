package com.quality.point.exception;

import com.quality.point.constant.ErrorDetails;
import com.quality.point.service.dto.ResponseDto;
import com.quality.point.utility.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class UserServiceGlobalExceptionHandler {

    @Autowired
    private ResponseUtil responseUtil;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleException(Exception exception) {
        log.error("Getting common server side exception and reason is: {}", exception.getMessage());
        return responseUtil.populateErrorResponse(ErrorDetails.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDto> handleRuntimeExceptionn(RuntimeException exception) {
        log.error("Getting common runtime exception and reason is: {}", exception.getMessage());
        return responseUtil.populateErrorResponse(ErrorDetails.RUNTIME_EXCEPTION);
    }

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<ResponseDto<?>> handleApplicationException(UserServiceException exception) {
        log.error("Getting application exception and reason is: {}", exception.getThrowable().getMessage());
        return responseUtil.populateErrorResponse(exception.getErrorDetails());
    }

}
