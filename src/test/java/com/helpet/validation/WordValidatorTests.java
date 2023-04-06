package com.helpet.validation;

import com.helpet.validation.annotation.Word;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class WordValidatorTests {
    private final static int minLength = 3;

    private final static int maxLength = 30;

    @Test
    public void testValid() {
        String[] tagNames = new String[]{
                "Ab2023",
                "Ab2023Ab",
                "1".repeat(minLength),
                "1".repeat(maxLength),
                "f".repeat(minLength),
                "f".repeat(maxLength),
                "F".repeat(minLength),
                "F".repeat(maxLength),
                "Г".repeat(minLength),
                "Г".repeat(maxLength),
                "г".repeat(minLength),
                "г".repeat(maxLength)
        };

        for (String tagName : tagNames) {
            Assert.assertTrue(isValidTag(Tag.of(tagName)));
        }
    }

    @Test
    public void testNotValid() {
        String[] tagNames = new String[]{
                null,
                "",
                "A A",
                "Abc=",
                "a".repeat(minLength - 1),
                "a".repeat(maxLength + 1)
        };

        for (String tagName : tagNames) {
            Assert.assertFalse(isValidTag(Tag.of(tagName)));
        }
    }

    private boolean isValidTag(Tag tag) {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = validatorFactory.usingContext().getValidator();
            Set<ConstraintViolation<Tag>> errors = validator.validate(tag);
            return errors.isEmpty();
        }
    }

    private record Tag(@Word(message = "Invalid tag", minLength = minLength, maxLength = maxLength) String name) {
        private Tag(String name) {
            this.name = name;
        }

        public static Tag of(String name) {
            return new Tag(name);
        }
    }
}
