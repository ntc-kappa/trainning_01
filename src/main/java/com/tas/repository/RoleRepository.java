package com.tas.repository;

import com.tas.entity.RoleEntity;
import com.tas.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByName(String name);

//    Set<RoleEntity> findByUserEntities();
}
