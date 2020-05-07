package com.tas.test;

import com.tas.dto.ProjectDto;
import com.tas.entity.ProjectEntity;
import com.tas.entity.UserEntity;
import com.tas.repository.ProjectRepository;
import com.tas.service.ProjectService;
import com.tas.service.UserService;
import com.tas.service.impl.ProjectServiceImpl;
import com.tas.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class TestMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService=applicationContext.getBean(UserServiceImpl.class);
        Integer[] ids=new Integer[]{19,20};
        System.out.println(userService.findEmail("dat@gmail.com"));



    }

}
