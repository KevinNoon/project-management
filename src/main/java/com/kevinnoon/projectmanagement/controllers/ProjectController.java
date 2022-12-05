package com.kevinnoon.projectmanagement.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kevinnoon.projectmanagement.dto.ProjectStage;
import com.kevinnoon.projectmanagement.dto.TimeChartData;
import com.kevinnoon.projectmanagement.entities.Employee;
import com.kevinnoon.projectmanagement.entities.Project;
import com.kevinnoon.projectmanagement.repository.EmployeeRepository;
import com.kevinnoon.projectmanagement.repository.ProjectRepository;
import com.kevinnoon.projectmanagement.services.EmployeeService;
import com.kevinnoon.projectmanagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/new")
    public String displayProjectForm(Model model){
        Project project = new Project();
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("project",project);
        model.addAttribute("allEmployees", employees);
        return "projects/new-project";
    }
    @GetMapping("")
    public String displayHome(Model model) {
        List<Project> projects = projectService.findAll();
        model.addAttribute("projects",projects);
        return "projects/list-projects";
    }
    @PostMapping("/save")
    public String createNewProject(Model model,@Valid Project project, Errors errors){
        if (errors.hasErrors()) {
            System.out.println(errors.getAllErrors().get(0));
            List<Employee> employees = employeeService.findAll();
            model.addAttribute("project",project);
            model.addAttribute("allEmployees", employees);
            return "projects/new-project";
        }
        projectService.save(project);
        return "redirect:/projects";
    }
    @GetMapping("/timelines")
    public String displayProjectTimeTimes(Model model) throws JsonProcessingException {
        List<TimeChartData> timeDate = projectService.getTimeData();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonTimeChartData = objectMapper.writeValueAsString(timeDate);
        model.addAttribute("projectTimeList",jsonTimeChartData);
        return "projects/project-timelines";
    }
    @GetMapping("/update")
    public String updateEmployee(@RequestParam("id") long id,Model model) throws ParseException {
        Project project = projectService.findByProjectId(id);
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("project",project);
        model.addAttribute("allEmployees", employees);
        return  "projects/new-project";
    }
    @GetMapping("/delete")
    public String deleteProject(@RequestParam("id") long id,Model model){
        if (projectService.getProjectById(id) != null){
            projectService.deleteById(id);
        }


        return "redirect:/projects";
    }
}
