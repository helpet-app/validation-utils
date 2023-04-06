package com.helpet.validation.validator;

import com.helpet.validation.annotation.Password;
import com.helpet.validation.validator.exception.MinLengthIsTooShortException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    private int minLength;

    @Override
    public void initialize(Password constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.minLength = constraintAnnotation.minLength();
        this.validateParameters();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.nonNull(s) && s.length() >= minLength;
    }

    private void validateParameters() {
        if (minLength < 1) {
            throw new MinLengthIsTooShortException(minLength, 1);
        }
    }
}
