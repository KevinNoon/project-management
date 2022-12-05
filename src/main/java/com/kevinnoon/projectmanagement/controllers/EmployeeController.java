package com.kevinnoon.projectmanagement.controllers;

import com.kevinnoon.projectmanagement.entities.Employee;
import com.kevinnoon.projectmanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/new")
    public String displayEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "employees/new-employee";
    }
    @GetMapping("")
    public String displayHome(Model model){
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("employees", employees);
        return "employees/list-employees";
    }

    @PostMapping("/save")
    public String createNewEmployee( Model model,@Valid Employee employee, Errors errors){
        if (errors.hasErrors())
            return "employees/new-employee";
        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("/update")
    public String updateEmployee(@RequestParam("id") long id,Model model){
        Employee employee = employeeService.findByEmployeeId(id);
        model.addAttribute("employee",employee);
        return  "employees/new-employee";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") long id,Model model){
        Employee employee = employeeService.findByEmployeeId(id);
        employeeService.deleteById(employee.getEmployeeId());
        return "redirect:/employees";
    }

}
