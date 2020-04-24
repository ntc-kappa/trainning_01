package com.tas.test;

import com.tas.service.ProjectService;
import com.tas.service.TypeProjectService;
import com.tas.service.impl.ProjectServiceImpl;
import com.tas.service.impl.TypeProjectServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        ProjectService service = applicationContext.getBean(ProjectServiceImpl.class);
        System.out.println(service.getById(1).getTitle());
    }
}
