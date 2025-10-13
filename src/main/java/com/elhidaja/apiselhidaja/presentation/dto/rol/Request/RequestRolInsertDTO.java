package com.elhidaja.apiselhidaja.presentation.dto.rol.Request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestRolInsertDTO {
    @NotBlank(message = "El nombre del rol no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del rol debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del rol debe contener solo letras y espacios")
    private String nombre;
}
