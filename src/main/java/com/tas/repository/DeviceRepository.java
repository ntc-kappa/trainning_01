package com.tas.repository;

import com.tas.entity.DeviceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Integer> {

    List<DeviceEntity> findAll();

    Page<DeviceEntity> findAll(Pageable pageable);

    DeviceEntity findOneById(Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE d FROM device d WHERE d.id IN :ids", nativeQuery = true)
    void deleteSomeDevice(@Param("ids") Integer[] ids);
}
