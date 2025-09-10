package com.in.company.exception;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String errorMsg){
        super(errorMsg);
    }
}
