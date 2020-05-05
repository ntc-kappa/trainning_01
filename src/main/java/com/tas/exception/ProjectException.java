package com.tas.exception;

public class ProjectException extends RuntimeException {
    public ProjectException(Integer id) {
        System.out.println("Project not found " + id);
    }

    public ProjectException(){
        System.out.println("Xảy ra lỗi khi xóa !");
    }
}
