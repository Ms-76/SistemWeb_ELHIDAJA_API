package com.elhidaja.apiselhidaja.util.validationsPersonalisate;

import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MayorDeEdadValidator implements ConstraintValidator<MayorDeEdad, LocalDate> {
    @Override
    public boolean isValid(LocalDate fechaNacimiento, ConstraintValidatorContext context) {
        if (fechaNacimiento == null) {
            return false; // o true si quieres que solo @NotNull valide
        }
        return fechaNacimiento.plusYears(18).isBefore(LocalDate.now())
                || fechaNacimiento.plusYears(18).isEqual(LocalDate.now());
    }
}
