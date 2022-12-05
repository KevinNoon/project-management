package com.kevinnoon.projectmanagement.services;

import com.kevinnoon.projectmanagement.dto.EmployeeProject;
import com.kevinnoon.projectmanagement.entities.Employee;
import com.kevinnoon.projectmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public List<EmployeeProject> employeeProjects(){
        return employeeRepository.employeeProjects();
    }

    public Employee getEmployeeById(long id){
        return employeeRepository.findById(id).get();
    }

    public void deleteById(long id){
        try {
            employeeRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e){

        }
    }
    public List<Employee> findByEmail(String email){
        return employeeRepository.findByEmail(email);
    }

    public Iterable<Employee> findPaginatedEmployees(Pageable pageable){
        return employeeRepository.findAll(pageable);
    }

    public Employee findByEmployeeId(long id) {
        return employeeRepository.findByEmployeeId(id);
    }
}
