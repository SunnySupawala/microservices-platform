package com.in.employee.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @GetMapping("/health-check")
    public ResponseEntity<String> getEmployeeHealthCheck(){
        return ResponseEntity.ok("Employee service is up ");
    }
}
