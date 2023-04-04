package com.helpet.validation.annotation;

import com.helpet.validation.validator.NameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NameValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Name.List.class)
public @interface Name {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int maxLength() default 64;

    @Documented
    @Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        Name[] value();
    }
}
