package com.tas.repository;

import com.tas.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
    List<ProjectEntity> findAll();

    ProjectEntity findOneById(Integer id);

    ProjectEntity save(ProjectEntity entity);
}
