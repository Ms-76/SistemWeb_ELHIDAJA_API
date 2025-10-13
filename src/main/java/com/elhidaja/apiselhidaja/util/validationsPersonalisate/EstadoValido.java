package com.elhidaja.apiselhidaja.util.validationsPersonalisate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EstadoValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EstadoValido {
    String message() default "Estado debe ser 0, 1, 2 o 3";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
