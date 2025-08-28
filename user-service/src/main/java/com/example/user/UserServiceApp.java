package com.example.user;

import com.netflix.discovery.EurekaNamespace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class UserServiceApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(UserServiceApp.class, args);
        System.out.println( "Hello World!" );
    }
}
