package com.tas.controller.api;

import com.tas.dto.ResponsiveDto;
import com.tas.exception.DepartmentException;
import com.tas.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/department")
public class DepartmentApi {

    @Autowired
    DepartmentService departmentService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?>getDelete(@PathVariable Integer id){
        boolean i = departmentService.deleteById(id);
        if(i == false){
            throw new DepartmentException(id);
        }
        ResponsiveDto responsiveDto = new ResponsiveDto();
        responsiveDto.setMessage("Delete " + id + " sucess!");
        responsiveDto.setPath("/api/v1/department/" +id);
        responsiveDto.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responsiveDto,HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<?>getDeleteAll(@RequestBody Integer[] ids){
        long i = departmentService.deleteMany(ids);
        if (i==0){
            throw new DepartmentException();
        }
        ResponsiveDto responsiveDto = new ResponsiveDto();
        responsiveDto.setMessage("Delete " + ids + " sucess!");
        responsiveDto.setPath("/api/v1/department/");
        responsiveDto.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(responsiveDto,HttpStatus.OK);
    }
}
