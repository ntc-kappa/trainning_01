package com.tas.converter;

import com.tas.dto.DeviceDto;
import com.tas.entity.DeviceEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeviceConverter {

    @Autowired
    private ModelMapper modelMapper;

    public DeviceDto toDto(DeviceEntity entity) {
        DeviceDto dto = modelMapper.map(entity, DeviceDto.class);
        dto.setNameCategory(entity.getCategoryEntity().getName());
        dto.setCodeCategory(entity.getCategoryEntity().getCode());
        return dto;
    }

    public DeviceEntity toEntity(DeviceDto dto) {
        return modelMapper.map(dto, DeviceEntity.class);
    }

    public List<DeviceDto> toListDto(List<DeviceEntity> entities) {
        List<DeviceDto> dtos = new ArrayList<>();
        for (DeviceEntity entity : entities) {
            dtos.add(this.toDto(entity));
        }
        return dtos;
    }
}
