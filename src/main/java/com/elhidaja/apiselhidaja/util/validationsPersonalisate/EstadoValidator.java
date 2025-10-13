package com.elhidaja.apiselhidaja.util.validationsPersonalisate;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EstadoValidator implements ConstraintValidator<EstadoValido, Integer> {
    private final List<Integer> valoresValidos = Arrays.asList(0, 1, 2, 3);

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null)
            return false; 
        return valoresValidos.contains(value);
    }
}
