package com.tas.repository;

import com.tas.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {

    List<ProjectEntity> findAll();

    ProjectEntity findOneById(Integer id);

    @Transactional
    ProjectEntity save(ProjectEntity entity);

    @Modifying
    @Transactional
    @Query(value = "DELETE p FROM project p WHERE p.id = ?1", nativeQuery = true)
    void deleteById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE p FROM project p WHERE p.id IN ?1", nativeQuery = true)
    void deleteSomeProject(Integer[] ids);

    Page<ProjectEntity> findAll(Pageable pageable);

    List<ProjectEntity> findByTitleContaining(String name);
}
