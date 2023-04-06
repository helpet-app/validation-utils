package com.helpet.validation.validator;

import com.helpet.validation.annotation.Username;
import com.helpet.validation.validator.exception.MaxLengthIsLessThanMinLengthException;
import com.helpet.validation.validator.exception.MinLengthIsTooShortException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator implements ConstraintValidator<Username, String> {
    private int minLength;

    private int maxLength;

    @Override
    public void initialize(Username constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.minLength = constraintAnnotation.minLength();
        this.maxLength = constraintAnnotation.maxLength();
        this.validateParameters();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(s)) {
            return false;
        }

        /*
         * (?!^\..*) - username does not start with a dot
         * (?!.*\.$) - username does not end with a dot
         * (?!.*\.\.) - username does not contain consecutive dots
         * (?!^_+$) - username is not a sequence of underscores
         * (?!^\d+$) - username is not a sequence of numbers
         */
        String regex = String.format("^(?!^\\..*)(?!.*\\.\\.)(?!.*\\.$)(?!^_+$)(?!^\\d+$)[\\w.]{%d,%d}$", minLength, maxLength);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s.toLowerCase());
        return matcher.matches();
    }

    private void validateParameters() {
        if (minLength < 1) {
            throw new MinLengthIsTooShortException(minLength, 1);
        }

        if (maxLength < minLength) {
            throw new MaxLengthIsLessThanMinLengthException(minLength, maxLength);
        }
    }
}
