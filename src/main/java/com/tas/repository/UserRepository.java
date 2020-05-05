package com.tas.repository;

import com.tas.entity.ProjectEntity;
import com.tas.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    UserEntity findById(Integer id);
    Page<UserEntity> findAll(Pageable pageable);


    @Modifying

    @Transactional

    @Query(value = "DELETE user FROM user  WHERE  user.id IN ?1", nativeQuery = true)

    void deleteSomeUser(Integer[] ids);


}
