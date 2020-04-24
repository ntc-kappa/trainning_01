package com.tas.controller;

import com.tas.dto.ProjectDto;
import com.tas.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = {"/manager-tranning/projects"}, method = RequestMethod.GET)
    public ModelAndView managerProjectsPage() {
        ModelAndView mav = new ModelAndView("project/list-project");
        mav.addObject("projects", projectService.getAll());
        return mav;
    }

    @RequestMapping(value = {"/manager-tranning/projects/{id}", "/manager-tranning/projects/"}, method = RequestMethod.GET)
    public ModelAndView editsProjectPage(@PathVariable(required = false) Integer id) {
        ProjectDto projectDto = new ProjectDto();
        if (id != null) {
            projectDto = projectService.getById(id);
        }
        ModelAndView mav = new ModelAndView("project/edits-project");
        mav.addObject("project", projectDto);
        return mav;
    }

}
