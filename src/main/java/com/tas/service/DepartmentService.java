package com.tas.service;

import com.tas.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> getAll();
    DepartmentDto getById(Integer id);

    DepartmentDto save(DepartmentDto dto);

    boolean deleteById(Integer id);
    long deleteMany(Integer[] ids);

    List<DepartmentDto> getAll(int numberOnPage);
    long getCount();

}

