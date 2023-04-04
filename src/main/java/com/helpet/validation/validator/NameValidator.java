package com.helpet.validation.validator;

import com.helpet.validation.annotation.Name;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator implements ConstraintValidator<Name, String> {
    private int maxLength;

    @Override
    public void initialize(Name constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.maxLength = constraintAnnotation.maxLength();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(s) || s.length() > maxLength) {
            return false;
        }

        Pattern namePattern = Pattern.compile("[A-Za-zА-Яа-яЁё]+(\\s[A-Za-zА-Яа-яЁё]+)*");
        Matcher nameMatcher = namePattern.matcher(s);
        return nameMatcher.matches();
    }
}
