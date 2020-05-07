package com.tas.converter;

import com.tas.dto.UserDto;
import com.tas.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Component
public class UserConverter {
    public UserDto toDto(UserEntity entity) {
        try {
            UserDto dto = new UserDto();
            dto.setId(entity.getId());
            dto.setFullName(entity.getFullName());
            dto.setUsername(entity.getUsername());
            dto.setPassword(entity.getPassword());
            dto.setAddress(entity.getAddress());
            dto.setEmail(entity.getEmail());
            dto.setPhoneNumber(entity.getPhoneNumber());
            dto.setGender(entity.isGender());
            dto.setActive(entity.isActive());
            dto.setAvatar(entity.getAvatar());
            return dto;
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public UserEntity toEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setFullName(dto.getFullName());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setAddress(dto.getAddress());
        entity.setEmail(dto.getEmail());
        entity.setGender(dto.isGender());
        entity.setActive(dto.isActive());
        entity.setAvatar(dto.getAvatar());
        entity.setCreateBy("Sesssion get");
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return entity;
    }



    public List<UserDto> toListDto(List<UserEntity> entities) {
        List<UserDto> dtos = new ArrayList<>();
        entities.forEach(item -> {
            if (item != null) {
                dtos.add(this.toDto(item));
            }
        });
        return dtos;
    }

    private String sqlDateToString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dateUtil = new java.util.Date(date.getTime());
        return simpleDateFormat.format(dateUtil);
    }

    private Date stringToSqlDate(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date dateUtil = simpleDateFormat.parse(str);
            java.sql.Date sql = new java.sql.Date(dateUtil.getTime());
            return sql;
        } catch (ParseException e) {
            return null;
        }
    }
}
