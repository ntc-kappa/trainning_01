package com.tas.repository;

import com.tas.entity.CategoryEntity;
import com.tas.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PositionRepository  extends JpaRepository<PositionEntity,Integer> {
    List<PositionEntity> findByNameContains(String name);
}
