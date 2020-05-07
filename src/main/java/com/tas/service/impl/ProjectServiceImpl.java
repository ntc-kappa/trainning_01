package com.tas.service.impl;

import com.tas.converter.ProjectConverter;
import com.tas.dto.ProjectDto;
import com.tas.entity.ProjectEntity;
import com.tas.entity.TypeProjectEntity;
import com.tas.repository.ProjectRepository;
import com.tas.repository.TypeProjectRepository;
import com.tas.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectConverter projectConverter;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TypeProjectRepository typeProjectRepository;

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

    @Override
    public ProjectDto save(ProjectDto projectDto) {
        TypeProjectEntity typeProjectEntity = typeProjectRepository.findOneByCode(projectDto.getTypeProjectCode());
        if (projectDto.getId() != null) { // udpate
            ProjectEntity projectEntity = projectRepository.findOneById(projectDto.getId());
            projectEntity.setTypeProjectEntity(typeProjectEntity);
            projectEntity.setModifyBy("Chưa biết");
            projectEntity.setModifyTime(new Timestamp(System.currentTimeMillis()));
            projectConverter.toEntityV2(projectEntity, projectDto);
            ProjectEntity result = projectRepository.save(projectEntity);
            return projectConverter.toDto(result);
        } else {//save
            ProjectEntity projectEntity = projectConverter.toEntity(projectDto);
            projectEntity.setTypeProjectEntity(typeProjectEntity);
            ProjectEntity result = projectRepository.save(projectEntity);
            return projectConverter.toDto(result);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        if (id == null)
            return false;
        try {
            projectRepository.delete(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public List<ProjectDto> getAll(int numberOnPage) {
        Pageable pageable = new PageRequest(numberOnPage, 4);
        List<ProjectEntity> entities = projectRepository.findAll(pageable).getContent();
        List<ProjectDto> dtos = projectConverter.toListDto(entities);
        return dtos;
    }

    @Override
    public long getCount() {
        return projectRepository.count();
    }

    @Override
    public long deleteMany(Integer[] ids) {
        try {
            projectRepository.deleteSomeProject(ids);
            return ids.length;
        } catch (Exception ex) {
            return 0;
        }
    }

}
