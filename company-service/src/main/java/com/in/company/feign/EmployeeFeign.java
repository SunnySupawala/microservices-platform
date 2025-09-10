package com.in.company.feign;


import com.in.company.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "employee-service", path = "/api", configuration = FeignConfig.class)
public interface EmployeeFeign {

    @GetMapping("/health-check")
    String getHealthCheckFromEmployee();
}
