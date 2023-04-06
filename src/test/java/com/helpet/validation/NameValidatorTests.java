package com.helpet.validation;

import com.helpet.validation.annotation.Name;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class NameValidatorTests {
    private final static int minLength = 3;

    private final static int maxLength = 30;

    @Test
    public void testValid() {
        String[] names = new String[]{
                "Иванов Иван Иванович",
                "a".repeat(minLength),
                "a".repeat(maxLength)
        };

        for (String name : names) {
            Assert.assertTrue(isValidUser(User.of(name)));
        }
    }

    @Test
    public void testNotValid() {
        String[] names = new String[]{
                null,
                "",
                "Иванов  Иван",
                "Иванов 1",
                "a".repeat(minLength - 1),
                "a".repeat(maxLength + 1)
        };

        for (String name : names) {
            Assert.assertFalse(isValidUser(User.of(name)));
        }
    }

    private boolean isValidUser(User user) {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = validatorFactory.usingContext().getValidator();
            Set<ConstraintViolation<User>> errors = validator.validate(user);
            return errors.isEmpty();
        }
    }

    private record User(@Name(message = "Invalid name", minLength = minLength, maxLength = maxLength) String name) {
        private User(String name) {
            this.name = name;
        }

        public static User of(String name) {
            return new User(name);
        }
    }
}
