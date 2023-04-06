package com.helpet.validation.validator.exception;

public class MinLengthIsTooShortException extends RuntimeException {
    public MinLengthIsTooShortException(int minLength, int requiredLength) {
        super("Invalid min length: " + minLength + "! Min length must be at least " + requiredLength);
    }
}
