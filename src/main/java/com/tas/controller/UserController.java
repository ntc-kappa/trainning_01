package com.tas.controller;

import com.tas.dto.ProjectDto;
import com.tas.dto.TypeProjectDto;
import com.tas.entity.UserEntity;
import com.tas.repository.UserRepository;
import com.tas.service.UserService;
import com.tas.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/manager-tranning/users"}, method = RequestMethod.GET)
    public ModelAndView managerUserPage(@RequestParam(required = false, name = "page", defaultValue = "1") Integer page) {
        ModelAndView mav = new ModelAndView("user/user-list");
        mav.addObject("users", userService.getAllEntity(page - 1));
        mav.addObject("totalPage", (long) (Math.ceil((double) userService.getCount() / 4)));
        return mav;
    }
    @RequestMapping(value = {"/manager-tranning/users/{id}", "/manager-tranning/users/"}, method = RequestMethod.GET)
    public ModelAndView editsUserPage(@PathVariable(required = false) Integer id) {
        List<UserEntity> user = userService.getAllEntity();
        UserEntity userEntity = new UserEntity();
        if (id != null) {
            userEntity = userService.findById(id);
        }
        ModelAndView mav = new ModelAndView("user/user-edit");
        mav.addObject("user", userEntity);

        return mav;
    }
    @RequestMapping(value = {"/manager-tranning/users/"}, method = RequestMethod.POST)
    public String submitFormProject(@ModelAttribute(name = "user") @Valid final UserEntity userEntity, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<UserEntity> userEntities = userService.getAllEntity();
            model.addAttribute("users", userEntities);
            return "user/user-edit";
        }
        userService.save(userEntity);
        return "redirect:/manager-tranning/users";
    }


}
