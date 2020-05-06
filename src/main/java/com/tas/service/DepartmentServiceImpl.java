package com.tas.service;

import com.tas.converter.DepartmentConverter;
import com.tas.dto.DepartmentDto;
import com.tas.entity.DepartmentEntity;
import com.tas.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentConverter departmentConverter;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentDto> getAll() {
        List<DepartmentEntity> entities = departmentRepository.findAll();
        List<DepartmentDto> dtos = departmentConverter.dtoList(entities);
        return dtos;
    }

    @Override
    public DepartmentDto getById(Integer id) {
        DepartmentEntity departmentEntity = departmentRepository.findOneById(id);
        DepartmentDto dto = departmentConverter.departmentDto(departmentEntity);
        return dto;
    }

    @Override
    public DepartmentDto save(DepartmentDto dto) {
        //update
        if (dto.getId() != null) {
            DepartmentEntity departmentEntity = departmentRepository.findOneById(dto.getId());
            departmentConverter.toEntities(departmentEntity, dto);
            DepartmentEntity result = departmentRepository.save(departmentEntity);
            return departmentConverter.departmentDto(result);
        } else { //save
            DepartmentEntity departmentEntity = departmentConverter.toEntity(dto);
            DepartmentEntity result = departmentRepository.save(departmentEntity);
            return departmentConverter.departmentDto(result);
        }
    }

    @Override
    public boolean deleteById(Integer id) {
        if (id == null) {
            return false;
        }
        try {
            departmentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public long deleteMany(Integer[] ids) {
        try {
            departmentRepository.deleteSomeDepartment(ids);
            return ids.length;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public List<DepartmentDto> getAll(int numberOnPage) {
        Pageable pageable = new PageRequest(numberOnPage, 5);
        List<DepartmentEntity> entities = departmentRepository.findAll(pageable).getContent();
        List<DepartmentDto> dtos = departmentConverter.dtoList(entities);
        return dtos;
    }

    @Override
    public long getCount() {
        return departmentRepository.count();
    }
}
