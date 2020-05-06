package com.tas.test;

import com.tas.repository.DepartmentRepository;
import com.tas.service.DepartmentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain1 {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        DepartmentRepository departmentRepository = applicationContext.getBean(DepartmentRepository.class);
        DepartmentService departmentService = applicationContext.getBean(DepartmentService.class);
//        System.out.println(departmentRepository.findAll().size());
//        System.out.println(departmentService.getById(1));
//        departmentRepository.deleteById(1);
//        System.out.println(departmentRepository.findAll().size());
        System.out.println(Math.ceil((double) departmentService.getCount()/5));
    }
}
