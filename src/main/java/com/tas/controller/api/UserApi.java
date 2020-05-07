package com.tas.controller.api;

import com.tas.dto.ReponsiveDto;
import com.tas.exception.ProjectException;
import com.tas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("/api/v1/users")
public class UserApi {
    @Autowired
    private UserService userService;
    @DeleteMapping("/{id}")
    public ResponseEntity<?> getDelete(@PathVariable Integer id) {
        boolean i = userService.delete(id);
        if (i == false)
            throw new ProjectException(id);
        ReponsiveDto reponsiveDto = new ReponsiveDto();
        reponsiveDto.setMessage("Delete " + id + " success !");
        reponsiveDto.setPath("/api/v1/users/" + id);
        reponsiveDto.setTimestamp(new Timestamp(System.currentTimeMillis()));
        reponsiveDto.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(reponsiveDto, HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<?> getDeletes(@RequestBody Integer[] ids) {
        long i = userService.deleteMany(ids);
        if (i == 0) {
            throw new ProjectException();
        }
        ReponsiveDto reponsiveDto = new ReponsiveDto();
        reponsiveDto.setMessage("Delete " + ids + " success !");
        reponsiveDto.setPath("/api/v1/users/");
        reponsiveDto.setTimestamp(new Timestamp(System.currentTimeMillis()));
        reponsiveDto.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(reponsiveDto, HttpStatus.OK);
    }
}
