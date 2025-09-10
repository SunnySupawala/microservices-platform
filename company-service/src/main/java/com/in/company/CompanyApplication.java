package com.in.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableFeignClients
public class CompanyApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CompanyApplication.class, args);
        System.out.println( "Hello World!" );
    }
}
