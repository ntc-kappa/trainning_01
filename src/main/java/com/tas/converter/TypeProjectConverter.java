package com.tas.converter;

import com.tas.dto.TypeProjectDto;
import com.tas.entity.ProjectEntity;
import com.tas.entity.TypeProjectEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TypeProjectConverter {
    public TypeProjectDto toDto(TypeProjectEntity entity) {
        if (entity == null) {
            return null;
        } else {
            TypeProjectDto dto = new TypeProjectDto();
            dto.setId(entity.getId());
            dto.setCode(entity.getCode());
            dto.setName(entity.getName());
            return dto;
        }
    }

    public List<TypeProjectDto> toListDto(List<TypeProjectEntity> entities) {
        List<TypeProjectDto> dtos = new ArrayList<>();
        entities.forEach(item -> {
            if (item != null) {
                dtos.add(this.toDto(item));
            }
        });
        return dtos;
    }

}
