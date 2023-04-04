package com.helpet.validation.validator;

import com.helpet.validation.annotation.Username;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator implements ConstraintValidator<Username, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(s)) {
            return false;
        }

        Pattern usernamePattern = Pattern.compile("[a-z_][a-z0-9._]{3,28}[a-z_]");
        Matcher usernameMatcher = usernamePattern.matcher(s.toLowerCase());
        return usernameMatcher.matches();
    }
}
