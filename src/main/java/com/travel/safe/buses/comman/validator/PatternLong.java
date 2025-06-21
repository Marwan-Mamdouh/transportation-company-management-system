package com.travel.safe.buses.comman.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = PatternLongValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PatternLong {

  String message() default "Invalid ssn format, it should start with 2 or 3 then 13 digits";

  String regexp();

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}