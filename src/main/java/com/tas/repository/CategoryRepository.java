package com.tas.repository;

import com.tas.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {
   void deleteCategoryEntitiesById(Integer id);
   void deleteById(Integer id);
   List<CategoryEntity> findByNameContains(String name);
}
