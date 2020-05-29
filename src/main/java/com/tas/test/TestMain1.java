package com.tas.test;

import com.tas.repository.DepartmentRepository;
import com.tas.service.DepartmentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.text.SimpleDateFormat;
import java.util.Date;

public class TestMain1 {
    public static void main(String[] args) {
        System.out.println(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
    }
}
