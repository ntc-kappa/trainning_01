package com.tas.repository;

import com.tas.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {
    List<DepartmentEntity> findAll();
}
