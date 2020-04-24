package com.tas.converter;

import com.tas.dto.ProjectDto;
import com.tas.entity.ProjectEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectConverter {

    public ProjectDto toDto(ProjectEntity entity) {
        try {
            ProjectDto dto = new ProjectDto();
            dto.setId(entity.getId());
            dto.setCheckin(entity.getCheckin());
            dto.setCheckout(entity.getCheckout());
            dto.setTitle(entity.getTitle());
            dto.setStatus(entity.getStatus());
            dto.setDescription(entity.getDescription());
            dto.setTypeProjectName(entity.getTypeProjectEntity().getName());
            return dto;
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public List<ProjectDto> toListDto(List<ProjectEntity> entities) {
        List<ProjectDto> dtos = new ArrayList<>();
        entities.forEach(item -> {
            if (item != null) {
                dtos.add(this.toDto(item));
            }
        });
        return dtos;
    }
}
