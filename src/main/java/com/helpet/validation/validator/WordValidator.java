package com.helpet.validation.validator;

import com.helpet.validation.annotation.Word;
import com.helpet.validation.validator.exception.MaxLengthIsLessThanMinLengthException;
import com.helpet.validation.validator.exception.MinLengthIsTooShortException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordValidator implements ConstraintValidator<Word, String> {
    private int minLength;

    private int maxLength;

    @Override
    public void initialize(Word constraintAnnotation) {
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

        String regex = String.format("^(?=.{%d,%d}$)([A-Za-zА-Яа-яЁё\\d]+)$", minLength, maxLength);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
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
