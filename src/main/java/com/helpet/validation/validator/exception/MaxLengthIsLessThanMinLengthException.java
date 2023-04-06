package com.helpet.validation.validator.exception;

public class MaxLengthIsLessThanMinLengthException extends RuntimeException {
    public MaxLengthIsLessThanMinLengthException(int minLength, int maxLength) {
        super("Invalid max length: " + maxLength + "! Max length must be greater than min length: " + minLength);
    }
}
