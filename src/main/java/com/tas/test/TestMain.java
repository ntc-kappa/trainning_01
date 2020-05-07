package com.tas.test;

import com.tas.entity.UserEntity;
import com.tas.repository.UserRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserRepository userRepository = context.getBean(UserRepository.class);
        UserEntity entity = userRepository.findOneByUsername("quangdtptit");

    }

}
