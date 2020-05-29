package com.tas.repository;

import com.tas.entity.ProjectEntity;
import com.tas.entity.UserEntity;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    UserEntity findById(Integer id);
    Page<UserEntity> findAll(Pageable pageable);


    @Modifying
    @Query(value = "DELETE user FROM user  WHERE  user.id IN ?1", nativeQuery = true)
    void deleteSomeUser(Integer[] ids);


    @Query(value = "SELECT * from user  WHERE  user.user_name = ?1", nativeQuery = true)
    UserEntity findByUsername(String username);


    @Query(value = "SELECT * from user  WHERE  user.email = ?1", nativeQuery = true)
    UserEntity findByEmail(String Email);

    UserEntity findOneByUsername(String username);
    @Transactional
    @Modifying
    @Query(value="DELETE from user  WHERE user.is_active=0",nativeQuery = true)
    void deleteNotActive();

}
