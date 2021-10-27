package com.fatih.restapi.service;

import com.fatih.restapi.data.models.Employee;
import com.fatih.restapi.data.payloads.request.EmployeeRequest;
import com.fatih.restapi.data.payloads.response.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IEmployeeService {
    MessageResponse createEmployee(EmployeeRequest employeeRequest);
    MessageResponse updateEmployee(Integer employeeId, EmployeeRequest employeeRequest);
    MessageResponse deleteEmployee(Integer employeeId);
    Employee getSingleEmployee(Integer employeeId);
    List<Employee> getAllEmployee();
}
