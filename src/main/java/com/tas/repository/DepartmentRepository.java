package com.tas.repository;

import com.tas.entity.DepartmentEntity;
import org.hibernate.validator.internal.metadata.aggregated.rule.OverridingMethodMustNotAlterParameterConstraints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Integer> {
    List<DepartmentEntity> findAll();

    DepartmentEntity findOneById(Integer id);

    @Transactional
    DepartmentEntity save(DepartmentEntity entity);

    @Modifying
    @Transactional
    @Query(value = "DELETE d FROM department d WHERE d.id = ?1", nativeQuery = true)
    void deleteById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE d FROM department d WHERE d.id IN ?1", nativeQuery = true)
    void deleteSomeDepartment(Integer[] ids);

    Page<DepartmentEntity> findAll(Pageable pageable);


}
