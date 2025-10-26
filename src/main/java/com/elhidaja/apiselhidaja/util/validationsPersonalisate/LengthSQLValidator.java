package com.elhidaja.apiselhidaja.util.validationsPersonalisate;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class LengthSQLValidator implements ConstraintValidator<LengthSQL, String> {

    private static final Map<String, Map<String, Integer>> cache = new HashMap<>();

    private final JdbcTemplate jdbcTemplate;

    private String tabla;
    private String columna;

    public LengthSQLValidator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void initialize(LengthSQL constraintAnnotation) {
        this.tabla = constraintAnnotation.tabla();
        this.columna = constraintAnnotation.columna();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true; 

        int maxLength = obtenerLongitudColumna(tabla, columna);

        if (value.length() > maxLength) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                String.format("El campo '%s' excede el tamaño permitido de %d caracteres", columna, maxLength)
            ).addConstraintViolation();
            return false;
        }

        return true;
    }

    private int obtenerLongitudColumna(String tabla, String columna) {
        String key = tabla.toLowerCase();

        if (!cache.containsKey(key)) {
            List<Map<String, Object>> filas = jdbcTemplate.queryForList(
                "SELECT COLUMN_NAME, CHARACTER_MAXIMUM_LENGTH FROM INFORMATION_SCHEMA.COLUMNS " +
                "WHERE TABLE_NAME = ? AND DATA_TYPE LIKE '%char%'", tabla
            );

            Map<String, Integer> mapa = new HashMap<>();
            for (Map<String, Object> fila : filas) {
                mapa.put(((String) fila.get("COLUMN_NAME")).toLowerCase(),
                         (Integer) fila.get("CHARACTER_MAXIMUM_LENGTH"));
            }

            cache.put(key, mapa);
        }

        return cache.get(key).getOrDefault(columna.toLowerCase(), Integer.MAX_VALUE);
    }
}