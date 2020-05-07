package com.tas.service;

import com.tas.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> getAll();

    ProjectDto getById(Integer id);

    ProjectDto save(ProjectDto projectDto);

    boolean deleteById(Integer id);

    List<ProjectDto> getAll(int numberOnPage);

    long getCount();

    long deleteMany(Integer[] ids);

    List<ProjectDto> getAll(String likeName);

}
