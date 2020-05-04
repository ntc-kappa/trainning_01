package com.tas.test;

import com.tas.entity.ProjectEntity;
import com.tas.repository.ProjectRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        ProjectRepository projectRepository = applicationContext.getBean(ProjectRepository.class);
        ProjectEntity entity1 = projectRepository.findOneById(1);
        ProjectEntity entity2 = projectRepository.findOneById(1);

        System.out.println(entity1.equals(entity2));
    }

}
