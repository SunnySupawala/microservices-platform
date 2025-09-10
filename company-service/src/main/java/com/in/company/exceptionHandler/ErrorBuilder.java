package com.in.company.exceptionHandler;


import lombok.Builder;
import lombok.Data;
import org.apache.http.HttpStatus;

@Builder
@Data
public class ErrorBuilder {

    String msg;
    String description;
    int httpStatus;
}
