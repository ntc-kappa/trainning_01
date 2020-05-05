package com.tas.repository;

import com.tas.entity.ProjectPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectPositionRepository extends JpaRepository<ProjectPositionEntity, Integer> {
    List<ProjectPositionEntity> findAll();
}
