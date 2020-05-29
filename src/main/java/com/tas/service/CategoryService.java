package com.tas.service;

import com.tas.dto.CategoryDto;
import com.tas.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAll();
    CategoryEntity dtoToEntity(CategoryDto categoryDto);
}
