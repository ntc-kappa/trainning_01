package com.tas.controller.api;

import com.tas.dto.ProjectDto;
import com.tas.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectApi {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public List<ProjectDto> getAll() {
        return projectService.getAll();
    }

}
