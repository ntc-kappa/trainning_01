package com.tas.service.impl;

import com.tas.converter.DeviceConverter;
import com.tas.dto.DeviceDto;
import com.tas.entity.CategoryEntity;
import com.tas.entity.DeviceEntity;
import com.tas.repository.CategoryRepository;
import com.tas.repository.DeviceRepository;
import com.tas.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceConverter deviceConverter;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<DeviceDto> getAll() {
        List<DeviceEntity> entities = deviceRepository.findAll();
        return deviceConverter.toListDto(entities);
    }

    @Override
    public List<DeviceDto> getAll(Integer pageNumber) {
        List<DeviceEntity> entities = deviceRepository.findAll(new PageRequest(pageNumber, 4)).getContent();
        List<DeviceDto> dtos = deviceConverter.toListDto(entities);
        return dtos;
    }

    @Override
    public DeviceDto getById(Integer id) {
        return deviceConverter.toDto(deviceRepository.findOneById(id));
    }

    @Override
    public DeviceDto edits(DeviceDto dto) {
        DeviceEntity entity = deviceConverter.toEntity(dto);
        CategoryEntity categoryEntity = categoryRepository.findOneByCode(dto.getCodeCategory());
        entity.setCategoryEntity(categoryEntity);
        entity = deviceRepository.save(entity);
        return deviceConverter.toDto(entity);
    }

    @Override
    public long getTotalPage() {
        long count = deviceRepository.count();
        return (long) Math.ceil((double) count / 4);
    }

    @Override
    public long deleteMany(Integer[] ids) {
        try {
            deviceRepository.deleteSomeDevice(ids);
            return ids.length;
        } catch (Exception ex) {
            return 0;
        }
    }
}
