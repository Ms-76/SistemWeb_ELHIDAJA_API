package com.elhidaja.apiselhidaja.presentation.dto.loginLogs.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLoginLogInsertDTO {

    @NotBlank(message = "El email no puede estar vacío")
    @LengthSQL(tabla = "entidad", columna = "email")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @LengthSQL(tabla = "usuario", columna = "password")
    private String password;
}
