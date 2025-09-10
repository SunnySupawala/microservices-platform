package com.in.company.exceptionHandler;

import com.in.company.exception.ResourceNotFound;
import org.apache.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorBuilder> handleResourceNotFound(ResourceNotFound resourceNotFound, WebRequest webRequest){
            ErrorBuilder errorBuilder = ErrorBuilder.builder().msg(resourceNotFound.getMessage())
                    .description("Resource not found")
                    .httpStatus(500)
                    .build();
            return new ResponseEntity<>(errorBuilder, HttpStatusCode.valueOf(500));
    }

    //Handle generic Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorBuilder> handleGenericException(Exception exception, WebRequest webRequest){
        ErrorBuilder errorBuilder = ErrorBuilder.builder().msg(exception.getMessage())
                .description("Resource not found")
                .httpStatus(500)
                .build();
        return new ResponseEntity<>(errorBuilder, HttpStatusCode.valueOf(500));
    }

}
