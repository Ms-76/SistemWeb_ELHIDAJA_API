package com.elhidaja.apiselhidaja.util.validationsPersonalisate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) return false;

        boolean longEnough = password.length() >= 12;
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSymbol = password.matches(".*[^a-zA-Z0-9].*"); 

        return longEnough && hasUpper && hasLower && hasNumber && hasSymbol;
    }
}
