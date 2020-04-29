package com.tas.exception;

import com.tas.dto.ReponsiveDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ProjectException.class})
    public ResponseEntity<?> notFound(Exception ex, HttpServletRequest request) {
        ReponsiveDto reponsiveDto = new ReponsiveDto();
        reponsiveDto.setMessage(ex.getMessage());
        reponsiveDto.setPath(request.getRequestURI());
        reponsiveDto.setTimestamp(new Timestamp(System.currentTimeMillis()));
        reponsiveDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(reponsiveDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
