/*
package com.in.company.aop;

import com.in.company.exception.ResourceNotFound;
import com.in.company.exceptionHandler.ErrorBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

// just for knowledge
@Aspect
@Component
public class ExceptionHandlingAspect {

    @Pointcut("execution(* com.in.company..*(..))")
    public void serviceLayer(){}

    @AfterThrowing(pointcut = "serviceLayer()", throwing = "ex", argNames = "joinPoint,webRequest")
    public void logException(JoinPoint joinPoint, WebRequest webRequest){
        System.out.println("[AOP] Exception in " + jp.getSignature() + " : " + ex.getMessage());

    }

    // ControllerAdvice
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFound ex, WebRequest request) {
        return null;
    }

}*/
