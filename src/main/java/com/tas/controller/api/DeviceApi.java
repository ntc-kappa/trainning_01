package com.tas.controller.api;

import com.tas.dto.ReponsiveDto;
import com.tas.exception.ProjectException;
import com.tas.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/v1/devices")
public class DeviceApi {

    @Autowired
    private DeviceService deviceService;

    @DeleteMapping("/")
    public ResponseEntity<?> getDeletes(@RequestBody Integer[] ids) {
        long i = deviceService.deleteMany(ids);
        if (i == 0) {
            throw new ProjectException();
        }
        ReponsiveDto reponsiveDto = new ReponsiveDto();
        reponsiveDto.setMessage("Delete " + ids + " success !");
        reponsiveDto.setPath("/api/v1/devices/");
        reponsiveDto.setTimestamp(new Timestamp(System.currentTimeMillis()));
        reponsiveDto.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(reponsiveDto, HttpStatus.OK);
    }
}
