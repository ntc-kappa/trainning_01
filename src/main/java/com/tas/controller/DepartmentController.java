package com.tas.controller;

import com.tas.dto.DepartmentDto;
import com.tas.repository.DepartmentRepository;
import com.tas.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    @RequestMapping(value = {"/department"}, method = RequestMethod.GET)
    public ModelAndView managerDepartmentPage(@RequestParam(required = false, name = "page", defaultValue = "1")Integer page) {
        ModelAndView mav = new ModelAndView("department/department_list");
        mav.addObject("list", departmentService.getAll(page-1));
        mav.addObject("totalPage",(long)(Math.ceil((double)departmentService.getCount()/5)));
        return mav;
    }

    @RequestMapping(value = {"/department/", "/department/{id}"}, method = RequestMethod.GET)
    public ModelAndView editDepartment(@PathVariable(required = false, name = "id") Integer id) {
        DepartmentDto departmentDto = new DepartmentDto();
        if (id != null) {
            departmentDto = departmentService.getById(id);
        }
        ModelAndView mav = new ModelAndView("department/department_edit");
        mav.addObject("editDepartment", departmentDto);
        return mav;
    }

    @RequestMapping(value = {"/department/"}, method = RequestMethod.POST)
    public String submitFormDepartment(@Valid final DepartmentDto departmentDto, BindingResult bindingResult, Model model){
        DepartmentDto result = departmentService.save(departmentDto);
        return "redirect:/department";
    }

}
