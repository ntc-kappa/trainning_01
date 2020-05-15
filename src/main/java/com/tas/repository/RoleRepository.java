package com.tas.repository;

import com.tas.entity.RoleEntity;
import com.tas.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    @Query(nativeQuery = true
            , value = "SELECT * FROM role R\n" +
            " JOIN user_role UR ON R.id = UR.id_role\n" +
            " JOIN user U ON U.id = UR.id_user\n" +
            " WHERE U.id = :userId")
    List<RoleEntity> findAllByIdUserEntity(@Param("userId") Integer userId);
}
