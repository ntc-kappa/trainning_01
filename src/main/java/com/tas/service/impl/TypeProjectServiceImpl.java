package com.tas.service.impl;

import com.tas.converter.TypeProjectConverter;
import com.tas.dto.TypeProjectDto;
import com.tas.entity.TypeProjectEntity;
import com.tas.repository.TypeProjectRepository;
import com.tas.service.TypeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeProjectServiceImpl implements TypeProjectService {

    @Autowired
    private TypeProjectRepository typeProjectRepository;

    @Autowired
    private TypeProjectConverter typeProjectConverter;

    @Override
    public List<TypeProjectDto> getAll() {
        List<TypeProjectEntity> entities = typeProjectRepository.findAll();
        List<TypeProjectDto> dtos = typeProjectConverter.toListDto(entities);
        return dtos;
    }

}
