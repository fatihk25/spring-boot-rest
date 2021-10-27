package com.fatih.restapi.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String employee, String id, Integer employeeId) {
    }
}
