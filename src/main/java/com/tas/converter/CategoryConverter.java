package com.tas.converter;

import com.tas.dto.CategoryDto;
import com.tas.entity.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CategoryDto toDto(CategoryEntity entity) {
        CategoryDto dto = modelMapper.map(entity, CategoryDto.class);
        return dto;
    }

    public CategoryEntity toEntity(CategoryDto dto) {
        CategoryEntity entity = modelMapper.map(dto, CategoryEntity.class);
        return entity;
    }

    public List<CategoryDto> toListDto(List<CategoryEntity> entities) {
        List<CategoryDto> result = new ArrayList<>();
        for (CategoryEntity entity : entities) {
            result.add(this.toDto(entity));
        }
        return result;
    }
}
