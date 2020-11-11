package com.tas.service.scheduing;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
@Component
@Aspect
public class DeleteUserAOP  {
    @Before("execution(* com.tas.repository.ProjectRepository.deleteById*(..))")
    public void afterThrowing(Exception e) throws Throwable {
        System.out.println("throw advice method: loi kia" );
    }

}
