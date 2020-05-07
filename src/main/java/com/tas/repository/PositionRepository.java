package com.tas.repository;

import com.tas.entity.CategoryEntity;
import com.tas.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository  extends JpaRepository<PositionEntity,Long> {
}
