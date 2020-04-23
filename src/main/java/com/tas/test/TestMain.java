package com.tas.test;

import com.tas.repository.DepartmentRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        DepartmentRepository departmentRepository = applicationContext.getBean(DepartmentRepository.class);
        System.out.println(departmentRepository.findAll().size());
    }
}
