package com.tas.repository;

import com.tas.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    List<CategoryEntity> findAll();

    CategoryEntity findOneByCode(String code);
}
