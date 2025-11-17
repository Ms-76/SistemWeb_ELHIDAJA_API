package com.elhidaja.apiselhidaja.util.validationsPersonalisate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = EstadoValidoValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface EstadoValido {
    String message() default "El estado no es válido. Solo se aceptan los valores 0, 1, 2, 3 o 4";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}