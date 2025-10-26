package com.elhidaja.apiselhidaja.configuration.interceptors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // errores generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex, HttpServletRequest request) {
        guardarError(null, request, ex);
        return generarRespuesta(500, "Error interno del servidor", ex.getMessage());
    }

    // validación (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        guardarError(null, request, ex);
        String mensaje = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return generarRespuesta(400, "Error de validación", mensaje);
    }

    // errores SQL o SP
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleSqlException(DataAccessException ex, HttpServletRequest request) {
        guardarError(null, request, ex);
        String mensaje = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();
        return generarRespuesta(500, "Error de base de datos", mensaje);
    }

    // inesrt
    private void guardarError(Integer idUsuario, HttpServletRequest request, Exception ex) {
        try {
            if (idUsuario == null) {
                idUsuario = extraerIdLogin(request);
            }

            jdbcTemplate.update(
                    "EXEC SP_insertar_log_error_api ?, ?, ?, ?, ?",
                    idUsuario,
                    request.getRequestURI(),
                    request.getMethod(),
                    ex.getMessage(),
                    ex.toString());
        } catch (Exception e) {
            System.err.println("Error al guardar log de error: " + e.getMessage());
        }
    }

    private Integer extraerIdLogin(HttpServletRequest request) {
        try (BufferedReader reader = request.getReader()) {
            String body = reader.lines().reduce("", (acc, line) -> acc + line);
            if (body.contains("\"idLogin\"")) {
                String idPart = body.split("\"idLogin\"\\s*:\\s*")[1]
                        .split("[,}]")[0]
                        .trim();
                return Integer.parseInt(idPart);
            }
        } catch (Exception e) {
            System.err.println("No se pudo extraer idLogin del body: " + e.getMessage());
        }
        return null;
    }

    // json de respuesta
    private ResponseEntity<Map<String, Object>> generarRespuesta(int status, String error, String mensaje) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("error", error);
        response.put("message", mensaje);
        response.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.valueOf(status));
    }
}