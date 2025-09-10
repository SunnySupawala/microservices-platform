package com.in.company.controller;

import com.in.company.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/health-check")
    public ResponseEntity<String> getEmployeeHealthCheck(){
        return ResponseEntity.ok("Company service is up ");
    }

    @GetMapping("feign/health-check")
    public ResponseEntity<String> getEmployeeFeignHealthCheck(){
        String result = employeeService.getEmployeeFeign();
        return ResponseEntity.ok(result);
    }


}
