package com.kevinnoon.projectmanagement.repository;

import com.kevinnoon.projectmanagement.dto.EmployeeProject;
import com.kevinnoon.projectmanagement.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "api-employees",path = "api-employees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {
    @Override
    public List<Employee> findAll();

    @Query(nativeQuery = true,value = "SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount " +
            "FROM employee e LEFT JOIN  project_employee pe ON pe.employee_id = e.employee_id " +
            "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
    public List<EmployeeProject> employeeProjects();

   // public Employee findByEmail(String value);
    public List<Employee> findByEmail(String value);

    public Page<Employee> findAll(Pageable pageable);

    Employee findByEmployeeId(long id);
}
