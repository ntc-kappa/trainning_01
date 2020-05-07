package com.tas.converter;

import com.tas.dto.ProjectDto;
import com.tas.entity.ProjectEntity;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProjectConverter {

    public ProjectDto toDto(ProjectEntity entity) {
        try {
            ProjectDto dto = new ProjectDto();
            dto.setId(entity.getId());
            dto.setCheckin(this.sqlDateToString(entity.getCheckin()));
            dto.setCheckout(this.sqlDateToString(entity.getCheckout()));
            dto.setTitle(entity.getTitle());
            dto.setStatus(entity.getStatus());
            dto.setDescription(entity.getDescription());
            dto.setTypeProjectName(entity.getTypeProjectEntity().getName());
            dto.setTypeProjectCode(entity.getTypeProjectEntity().getCode());
            return dto;
        } catch (NullPointerException ex) {
            return null;
        }
    }

    public ProjectEntity toEntity(ProjectDto dto) {
        ProjectEntity entity = new ProjectEntity();
        entity.setCreateBy("Sesssion get");
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        entity.setCheckin(this.stringToSqlDate(dto.getCheckin()));
        entity.setCheckout(this.stringToSqlDate(dto.getCheckout()));
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    public void toEntityV2(ProjectEntity entity, ProjectDto dto) {
        entity.setCheckin(this.stringToSqlDate(dto.getCheckin()));
        entity.setCheckout(this.stringToSqlDate(dto.getCheckout()));
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
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
