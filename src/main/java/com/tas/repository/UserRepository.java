package com.tas.repository;

import com.tas.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    UserEntity findOneByUsername(String name);



}
