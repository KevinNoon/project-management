package com.kevinnoon.projectmanagement.api.controllers;

import com.kevinnoon.projectmanagement.entities.Employee;

import com.kevinnoon.projectmanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeAPiController {
    @Autowired
    EmployeeService service;

    @GetMapping
    public List<Employee> employees(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Employee employee(@PathVariable("id") long id){
        return service.getEmployeeById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody @Valid Employee employee){
        return service.save(employee);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee update(@RequestBody @Valid Employee employee){
        return service.save(employee);
    }

    @PatchMapping(path = "/{id}",consumes = "application/json")
    public Employee partialUpdate(@PathVariable("id") long id, @RequestBody Employee patchEmployee){
        Employee employee = service.getEmployeeById(id);
        if (patchEmployee.getEmail() != null){
            employee.setEmail(patchEmployee.getEmail());
        }
        if (patchEmployee.getFirstName() != null){
            employee.setFirstName(patchEmployee.getFirstName());
        }
        if (patchEmployee.getEmail() != null){
            employee.setLastName(patchEmployee.getLastName());
        }
        return service.save(employee);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id){
        service.deleteById(id);
    }

    @GetMapping(params = {"page","size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Employee> findPaginatedEmployees(@RequestParam("page") int page,
                                                     @RequestParam("size") int size){
        Pageable pageAndSize = PageRequest.of(page,size);
        return service.findPaginatedEmployees(pageAndSize);
    }
}
