package com.tas.controller.api;

import com.tas.dto.ReponsiveDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserApi {

    @PutMapping("/")
    public ReponsiveDto changeForget(@RequestParam String oldPassword,
                                     @RequestParam String newPassword) {

        return null;
    }
}
