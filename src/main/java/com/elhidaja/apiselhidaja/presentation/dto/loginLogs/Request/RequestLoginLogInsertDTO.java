package com.elhidaja.apiselhidaja.presentation.dto.loginLogs.Request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLoginLogInsertDTO {

    @NotBlank(message = "El email no puede estar vacío")
    @Size(max = 255, message = "El email no debe exceder 255 caracteres")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;
}
