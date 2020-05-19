package com.tas.controller;

import com.tas.entity.UserEntity;
import com.tas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
public class AdviceController {
    @Autowired
    private UserRepository userRepository;

    @ModelAttribute
    public void handle(Model model, Principal principal){
    if (principal != null){
        UserEntity entity=userRepository.findOneByUsername(principal.getName());
        model.addAttribute("fullName",entity.getFullName());

    }
    }
}
