package com.fatih.restapi.service;


import com.fatih.restapi.data.models.Employee;
import com.fatih.restapi.data.payloads.request.EmployeeRequest;
import com.fatih.restapi.data.payloads.response.MessageResponse;
import com.fatih.restapi.data.repository.EmployeeRepository;
import com.fatih.restapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public MessageResponse createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setEmail(employeeRequest.getEmail());
        employee.setSalary(employeeRequest.getSalary());
        employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        employee.setDepartment(employeeRequest.getDepartment());
        employeeRepository.save(employee);
        return new MessageResponse("Employee Created");
    }

    @Override
    public MessageResponse updateEmployee(Integer employeeId, EmployeeRequest employeeRequest) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if(employee.isEmpty()) {
            throw new ResourceNotFoundException("Employee", "id", employeeId);
        }
        employee.get().setFirstName(employeeRequest.getFirstName());
        employee.get().setLastName(employeeRequest.getLastName());
        employee.get().setPhoneNumber(employeeRequest.getPhoneNumber());
        employee.get().setEmail(employeeRequest.getEmail());
        employee.get().setSalary(employeeRequest.getSalary());
        employee.get().setDepartment(employeeRequest.getDepartment());
        employeeRepository.save(employee.get());
        return new MessageResponse("Data Updated");
    }

    @Override
    public MessageResponse deleteEmployee(Integer employeeId) {
        if (employeeRepository.getById(employeeId).getId().equals(employeeId)){
            employeeRepository.deleteById(employeeId);
            return new MessageResponse("Data Deleted");
        } else
            return new MessageResponse("Data not found");
    }

    @Override
    public Employee getSingleEmployee(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() ->
                new ResourceNotFoundException("Employee", "id", employeeId));
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }
}
