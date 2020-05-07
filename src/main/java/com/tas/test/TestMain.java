package com.tas.test;

import com.tas.entity.CategoryEntity;
import com.tas.entity.RoleEntity;
import com.tas.entity.UserEntity;
import com.tas.repository.CategoryRepository;
import com.tas.repository.RoleRepository;
import com.tas.repository.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
//        DepartmentRepository departmentRepository = applicationContext.getBean(DepartmentRepository.class);
//        System.out.println(departmentRepository.findAll().size());
//        Properties properties=applicationContext.getBean(Properties.class);
//        DataSource dataSource=applicationContext.getBean(DataSource.class);
        CategoryRepository repository=applicationContext.getBean(CategoryRepository.class);
        for(int i=1;i<50;i++) {
            CategoryEntity  entity=new CategoryEntity();
            entity.setName("thiết bị " +i);

            entity.setDescription("okokokokokok");
            repository.save(entity);
        }

//        CategroryRepository repository=applicationContext.getBean(CategroryRepository.class);
//        repository.save(entity);
//        UserRepository userRepository=applicationContext.getBean(UserRepository.class);
//        UserEntity entity=userRepository.findOne(1);
//        System.out.println(entity.getUsername());
//        System.out.println(entity.getRoleEntities().size());

    }
}
