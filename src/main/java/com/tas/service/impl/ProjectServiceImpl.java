package com.tas.service.impl;

import com.tas.converter.ProjectConverter;
import com.tas.dto.ProjectDto;
import com.tas.entity.ProjectEntity;
import com.tas.repository.ProjectRepository;
import com.tas.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectConverter projectConverter;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<ProjectDto> getAll() {
        List<ProjectEntity> entities = projectRepository.findAll();
        List<ProjectDto> dtos = projectConverter.toListDto(entities);
        return dtos;
    }

    @Override
    public ProjectDto getById(Integer id) {
        ProjectEntity projectEntity = projectRepository.findOneById(id);
        ProjectDto dto = projectConverter.toDto(projectEntity);
        return dto;
    }
}
