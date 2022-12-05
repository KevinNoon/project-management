package com.kevinnoon.projectmanagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kevinnoon.projectmanagement.validators.UniqueValue;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Employee {
    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name="employee_seq",sequenceName="employee_seq", allocationSize = 1)
    private long employeeId;
    @NotBlank(message = " * Must not be blank")
    @Size(min = 2,max = 50, message = " * Must be between 2 and 50 chars")
    private String firstName;
    @NotBlank(message = " * Must not be blank")
    @Size(min = 2,max = 50, message = " * Must be between 2 and 50 chars")
    private String lastName;
    @NotBlank(message = " * Must not be blank")
    @Email
    @UniqueValue
    private String email;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,
            CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    @JsonIgnore
    private List<Project> projects;
}
