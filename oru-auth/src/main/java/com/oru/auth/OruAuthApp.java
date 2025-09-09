package com.oru.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class OruAuthApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(OruAuthApp.class, args);
        System.out.println( "Hello World!" );
    }
}
