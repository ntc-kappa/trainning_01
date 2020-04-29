package com.tas.test;

import com.tas.dto.ProjectDto;
import com.tas.entity.ProjectEntity;
import com.tas.repository.ProjectRepository;
import com.tas.service.ProjectService;
import com.tas.service.impl.ProjectServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class TestMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        ProjectService projectService = applicationContext.getBean(ProjectServiceImpl.class);
        List<ProjectDto> list = projectService.getAll(0);
//        for(ProjectDto dto : list){
//            System.out.println(dto.getId()+"-"+dto.getTitle()+"-"+);
//
//        }
    }

}
