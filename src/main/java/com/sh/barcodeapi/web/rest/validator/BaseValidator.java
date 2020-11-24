package com.sh.barcodeapi.web.rest.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.text.MessageFormat;

public abstract class BaseValidator<C extends Annotation, T> implements ConstraintValidator<C, T> {
    protected void addViolation(ConstraintValidatorContext context, String property, String message) {
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(property)
                .addConstraintViolation();
    }

    protected void addViolation(
            ConstraintValidatorContext context, String property, String messageTemplate, Object... params) {
        context.buildConstraintViolationWithTemplate(MessageFormat.format(messageTemplate, params))
                .addPropertyNode(property)
                .addConstraintViolation();
    }
}
