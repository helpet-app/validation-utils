package com.helpet.validation.annotation;

import com.helpet.validation.validator.WordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = WordValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Word.List.class)
public @interface Word {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int minLength() default 1;

    int maxLength() default 30;

    @Documented
    @Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        Word[] value();
    }
}
