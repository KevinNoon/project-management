package com.kevinnoon.projectmanagement.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevinnoon.projectmanagement.dto.EmployeeProject;
import com.kevinnoon.projectmanagement.dto.ProjectStage;
import com.kevinnoon.projectmanagement.entities.Project;
import com.kevinnoon.projectmanagement.services.EmployeeService;
import com.kevinnoon.projectmanagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    ProjectService projectService;
    @Autowired
    EmployeeService employeeService;
    @Value("${version:default value} ")
    private String ver;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {

        model.addAttribute("version",ver);
        Map<String,Override> map = new HashMap<>();
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects",projects);
        List<EmployeeProject> employeeProjectCnt = employeeService.employeeProjects();
        model.addAttribute("employeeProjectCnt", employeeProjectCnt);
        ObjectMapper objectMapper = new ObjectMapper();
        List<ProjectStage> projectStage = projectService.getProjectStage();
        String jSonString = objectMapper.writeValueAsString(projectStage);
        model.addAttribute("projectStatus",jSonString);
        return "main/home";
    }
}
