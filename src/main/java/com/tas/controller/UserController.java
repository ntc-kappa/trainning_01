package com.tas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @RequestMapping(value = "/change-password", method = RequestMethod.GET)
    public String changePasswordPage() {
        return "user/change-password";
    }

}
