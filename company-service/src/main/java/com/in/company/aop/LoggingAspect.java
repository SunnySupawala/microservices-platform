package com.in.company.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    //pointcut for all method in services layer
    @Pointcut("execution(* com.in.company..service..*(..))")
    public void employeeServices(){}

    @Before("employeeServices()")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("before method "+ joinPoint.getSignature().getName());
    }

    @After("employeeServices()")
    public void logAfter(JoinPoint joinPoint){
        System.out.println("Log after " + joinPoint.getSignature().getName());
    }

    @Around("employeeServices()")
    public Object logExecutionime(ProceedingJoinPoint pjp) throws Throwable{
        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long end = System.currentTimeMillis();
        System.out.println(pjp.getSignature() + " executed in " + (end - start) + "ms");
        return obj;
    }
}
