package com.in.company.service;

import com.in.company.exception.ResourceNotFound;
import com.in.company.feign.EmployeeFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeFeign employeeFeign;

    public String getEmployeeFeign(){

        String result = employeeFeign.getHealthCheckFromEmployee();
        /*if(result != null){
            throw new ResourceNotFound("ResourceNotAvailable");
        }*/
        return result;
    }
}
