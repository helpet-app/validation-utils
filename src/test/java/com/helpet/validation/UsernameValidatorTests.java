package com.helpet.validation;

import com.helpet.validation.annotation.Username;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class UsernameValidatorTests {
    private final static int minLength = 1;

    private final static int maxLength = 10;

    @Test
    public void testValid() {
        String[] usernames = new String[]{
                "_abc_",
                "a1234",
                "1abc_",
                "a".repeat(minLength),
                "a".repeat(maxLength)
        };

        for (String username : usernames) {
            System.out.println(username);
            Assert.assertTrue(isValidUser(User.of(username)));
        }
    }

    @Test
    public void testNotValid() {
        String[] usernames = new String[]{
                null,
                "",
                "_abc.",
                ".abc_",
                ".ab..c_",
                "_".repeat(minLength),
                "1".repeat(minLength),
                "a".repeat(minLength - 1),
                "a".repeat(maxLength + 1)
        };

        for (String username : usernames) {
            System.out.println(username);
            Assert.assertFalse(isValidUser(User.of(username)));
        }
    }

    private boolean isValidUser(User user) {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = validatorFactory.usingContext().getValidator();
            Set<ConstraintViolation<User>> errors = validator.validate(user);
            return errors.isEmpty();
        }
    }

    private record User(@Username(message = "Invalid username", minLength = minLength, maxLength = maxLength) String username) {
        private User(String username) {
            this.username = username;
        }

        public static User of(String username) {
            return new User(username);
        }
    }
}
