package com.tas.converter;

import com.tas.dto.DepartmentDto;
import com.tas.entity.DepartmentEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartmentConverter {
    public DepartmentDto departmentDto (DepartmentEntity entity){
        try {
            DepartmentDto dto = new DepartmentDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setLocation(entity.getLocation());
            return dto;
        }catch (NullPointerException exception){
            return null;
        }
    }

    public DepartmentEntity toEntity(DepartmentDto dto){
        DepartmentEntity entity = new DepartmentEntity();
        entity.setName(dto.getName());
        entity.setLocation(dto.getLocation());
        return entity;
    }

    public void toEntities(DepartmentEntity entity, DepartmentDto dto){
        entity.setName(dto.getName());
        entity.setLocation(dto.getLocation());
    }

    public List<DepartmentDto> dtoList (List<DepartmentEntity> entities){
        List<DepartmentDto> dtos = new ArrayList<>();
        entities.forEach(item -> {
            if (item != null){
                dtos.add(this.departmentDto(item));
            }
        });
        return dtos;
    }
}
