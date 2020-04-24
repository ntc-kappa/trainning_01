package com.tas.test;

import com.tas.entity.CategoryEntity;
import com.tas.repository.CategroryRepository;
import com.tas.repository.DepartmentRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import java.util.Properties;

public class TestMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
//        DepartmentRepository departmentRepository = applicationContext.getBean(DepartmentRepository.class);
//        System.out.println(departmentRepository.findAll().size());
//        Properties properties=applicationContext.getBean(Properties.class);
//        DataSource dataSource=applicationContext.getBean(DataSource.class);
        CategoryEntity entity=new CategoryEntity();
        entity.setName("Quang");
        CategroryRepository repository=applicationContext.getBean(CategroryRepository.class);
        repository.save(entity);
    }
}
