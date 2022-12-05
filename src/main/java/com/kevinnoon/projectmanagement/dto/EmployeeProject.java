package com.kevinnoon.projectmanagement.dto;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface EmployeeProject {
    public String getFirstName();
    public String getLastName();
    public String getProjectCount();
}
