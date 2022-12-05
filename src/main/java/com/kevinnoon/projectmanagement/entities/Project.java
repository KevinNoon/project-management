package com.kevinnoon.projectmanagement.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
//@ToString
@NoArgsConstructor
@Entity
public class Project {
    public Project(String name, String stage, String description) {
        this.name = name;
        this.stage = stage;
        this.description = description;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_seq")
    @SequenceGenerator(name="project_seq",sequenceName="project_seq", allocationSize = 1)
    private long projectId;
    @NotBlank(message = " * Must not be blank")
    @Size(min = 2,max = 50, message = " * Must be between 2 and 50 chars")
    private String name;

    private String stage;
    @NotBlank(message = " * Must not be blank")
    @Size(min = 2,max = 50, message = " * Must be between 2 and 50 chars")
    private String description;
    //@NotBlank(message = " * Must not be blank")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    //@NotBlank(message = " * Must not be blank")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,
            CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    @JsonIgnore
    private List<Employee> employees;
    public void addEmployee(Employee emp){
        if(employees == null){
            employees = new ArrayList<>();
        }
        employees.add(emp);
    }
    @InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null,  new CustomDateEditor(dateFormat, true));
    }
}
