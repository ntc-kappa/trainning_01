package com.tas.exception;

public class DepartmentException extends RuntimeException {
    public DepartmentException(Integer id) {
        System.out.println("Not found!!!" + id);
    }

    public DepartmentException() {
        System.out.println("Error when deleted!!!");
    }
}
