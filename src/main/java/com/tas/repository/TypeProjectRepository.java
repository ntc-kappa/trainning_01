package com.tas.repository;

import com.tas.entity.TypeProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeProjectRepository extends JpaRepository<TypeProjectEntity, Integer> {
    List<TypeProjectEntity> findAll();

    TypeProjectEntity findOneById(Integer id);

    TypeProjectEntity findOneByCode(String code);
}
