package com.tas.repository;

import com.tas.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {

}
