package com.tas.controller;

import com.tas.dto.ProjectDto;
import com.tas.dto.TypeProjectDto;
import com.tas.service.ProjectService;
import com.tas.service.TypeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TypeProjectService typeProjectService;

    @RequestMapping(value = {"/manager-tranning/projects"}, method = RequestMethod.GET)
    public ModelAndView managerProjectsPage() {
        ModelAndView mav = new ModelAndView("project/list-project");
        mav.addObject("projects", projectService.getAll());
        return mav;
    }

    @RequestMapping(value = {"/manager-tranning/projects/{id}", "/manager-tranning/projects/"}, method = RequestMethod.GET)
    public ModelAndView editsProjectPage(@PathVariable(required = false) Integer id) {
        List<TypeProjectDto> typeProjectDtoList = typeProjectService.getAll();
        ProjectDto projectDto = new ProjectDto();
        if (id != null) {
            projectDto = projectService.getById(id);
        }
        ModelAndView mav = new ModelAndView("project/edits-project");
        mav.addObject("project", projectDto);
        mav.addObject("typeProjects", typeProjectDtoList);
        return mav;
    }

    @RequestMapping(value = {"/manager-tranning/projects/"}, method = RequestMethod.POST)
    public String submitFormProject(@ModelAttribute(name = "project") @Valid final ProjectDto projectDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<TypeProjectDto> typeProjectDtoList = typeProjectService.getAll();
            model.addAttribute("typeProjects", typeProjectDtoList);
            return "project/edits-project";
        }
        ProjectDto result = projectService.save(projectDto);
        return "redirect:/manager-tranning/projects";
    }

}
