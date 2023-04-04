package com.helpet.validation.validator;

import com.helpet.validation.annotation.Name;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator implements ConstraintValidator<Name, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(s)) {
            return false;
        }

        Pattern namePattern = Pattern.compile("[A-Za-zА-Яа-яЁё](\\s[A-Za-zА-Яа-яЁё])*");
        Matcher nameMatcher = namePattern.matcher(s.toLowerCase());
        return nameMatcher.matches();
    }
}
