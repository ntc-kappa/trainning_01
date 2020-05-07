package com.tas.service.impl;

import com.tas.converter.CategoryConverter;
import com.tas.dto.CategoryDto;
import com.tas.entity.CategoryEntity;
import com.tas.repository.CategoryRepository;
import com.tas.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public List<CategoryDto> getAll() {
        List<CategoryEntity> entities = categoryRepository.findAll();
        return categoryConverter.toListDto(entities);
    }

}
