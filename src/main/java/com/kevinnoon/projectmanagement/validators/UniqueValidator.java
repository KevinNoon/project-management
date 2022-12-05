package com.kevinnoon.projectmanagement.validators;

import com.kevinnoon.projectmanagement.entities.Employee;
import com.kevinnoon.projectmanagement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValidator implements ConstraintValidator<UniqueValue,String> {
    @Autowired
    EmployeeService employeeService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        System.out.println("In is Valid");
        List<Employee> employees = employeeService.findByEmail(value);
        if (!employees.isEmpty())
            return false;
        else
            return true;
    }
}
