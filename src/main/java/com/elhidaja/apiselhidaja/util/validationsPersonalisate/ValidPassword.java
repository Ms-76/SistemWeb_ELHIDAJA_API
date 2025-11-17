package com.elhidaja.apiselhidaja.util.validationsPersonalisate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ FIELD })
@Retention(RUNTIME)
public @interface ValidPassword {

    String message() default "La contraseña debe tener al menos 12 caracteres, incluir mayúsculas, minúsculas, números y símbolos.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
