package com.elhidaja.apiselhidaja.presentation.dto.oficio.Request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOficioInsertDTO {
    @NotBlank(message = "El nombre del oficio no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del oficio debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del oficio debe contener solo letras y espacios")
    private String nombre;
}
