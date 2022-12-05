package com.kevinnoon.projectmanagement.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.groups.Default;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
public @interface UniqueValue {
    String  message () default  "Email already exists" ;
    Class <?>[] groups () default {};
    Class <? extends  Payload >[] payload () default {};
}
