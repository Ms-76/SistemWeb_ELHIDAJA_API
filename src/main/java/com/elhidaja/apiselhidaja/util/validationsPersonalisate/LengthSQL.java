package com.elhidaja.apiselhidaja.util.validationsPersonalisate;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LengthSQLValidator.class)
@Documented
public @interface LengthSQL {
     String message() default "El campo excede el tamaño permitido en la base de datos";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

   
    String tabla();
    String columna();
}
