package com.tas.service.scheduing;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
@Component
public class DeleteUserAOP implements ThrowsAdvice {
    public void afterThrowing(Exception e) throws Throwable {
        System.out.println("throw advice method: loi kia" );
    }

}
