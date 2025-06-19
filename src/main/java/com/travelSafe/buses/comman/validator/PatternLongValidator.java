package com.travelSafe.buses.comman.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotNull;
import java.util.regex.Pattern;

public class PatternLongValidator implements ConstraintValidator<PatternLong, @NotNull Long> {

  @NotNull
  private Pattern pattern;

  @Override
  public void initialize(PatternLong ann) {
    pattern = Pattern.compile(ann.regexp());
  }

  @Override
  public boolean isValid(Long value, ConstraintValidatorContext ctx) {
    return pattern.matcher(Long.toString(value)).matches();
  }
}