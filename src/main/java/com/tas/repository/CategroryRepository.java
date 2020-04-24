package com.tas.repository;

import com.tas.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategroryRepository  extends JpaRepository<CategoryEntity,Long> {

}
