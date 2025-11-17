package com.elhidaja.apiselhidaja.util.validationsPersonalisate;

import java.util.Set;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EstadoValidoValidator implements ConstraintValidator<EstadoValido, Long> {

    private final Set<Long> validValues = Set.of(0L, 1L, 2L, 3L, 4L);

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return validValues.contains(value);
    }
}