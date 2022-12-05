package com.kevinnoon.projectmanagement.api.controllers;

import com.kevinnoon.projectmanagement.entities.Employee;
import com.kevinnoon.projectmanagement.entities.Project;
import com.kevinnoon.projectmanagement.services.EmployeeService;
import com.kevinnoon.projectmanagement.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectAPiController {
    @Autowired
    ProjectService service;

    @GetMapping
    public List<Project> employees(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Project project(@PathVariable("id") long id){
        return service.getProjectById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestBody Project project){
        return service.save(project);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Project update(@RequestBody Project project){
        return service.save(project);
    }

    @PatchMapping(path = "/{id}",consumes = "application/json")
    public Project partialUpdate(@PathVariable("id") long id, @RequestBody Project patchProject){
        Project project = service.getProjectById(id);
        if (patchProject.getName() != null){
            project.setName(patchProject.getName());
        }
        if (patchProject.getDescription() != null){
            project.setDescription(patchProject.getDescription());
        }
        if (patchProject.getStage() != null){
            project.setStage(patchProject.getStage());
        }
        return service.save(project);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id){
        service.deleteById(id);
    }
}
