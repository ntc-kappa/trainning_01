package com.tas.service;

import com.tas.dto.DeviceDto;

import java.util.List;

public interface DeviceService {
    List<DeviceDto> getAll();

    List<DeviceDto> getAll(Integer pageNumber);

    DeviceDto getById(Integer id);

    DeviceDto edits(DeviceDto dto);

    long getTotalPage();

    long deleteMany(Integer[] ids);
}
