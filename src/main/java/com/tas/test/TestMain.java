package com.tas.test;

import com.tas.entity.ProjectEntity;
import com.tas.repository.ProjectRepository;
import com.tas.service.DeviceService;
import com.tas.service.impl.DeviceServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {

    public static void main(String[] args) {
        StringBuilder str1 = new StringBuilder("Hello");
        StringBuilder str2 = new StringBuilder("Hello");
        System.out.println(str1 == str2);
    }

}
