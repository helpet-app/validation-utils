package com.helpet.validation.validator;

import com.helpet.validation.annotation.Word;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordValidator implements ConstraintValidator<Word, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(s)) {
            return false;
        }

        Pattern wordPattern = Pattern.compile("[A-Za-zА-Яа-яЁё\\d]+");
        Matcher wordMatcher = wordPattern.matcher(s);
        return wordMatcher.matches();
    }
}
