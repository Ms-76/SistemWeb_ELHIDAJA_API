package com.elhidaja.apiselhidaja.presentation.dto.puesto.Request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPuestoInsertDTO {
    @NotBlank(message = "El nombre del puesto no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre del puesto debe tener entre 3 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del puesto debe contener letras y espacios")
    private String nombre;
}
