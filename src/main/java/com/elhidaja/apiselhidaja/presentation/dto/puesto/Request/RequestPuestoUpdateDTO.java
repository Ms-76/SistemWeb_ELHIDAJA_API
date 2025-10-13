package com.elhidaja.apiselhidaja.presentation.dto.puesto.Request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestPuestoUpdateDTO {
    @NotNull(message = "El idPuesto es obligatorio")
    @Min(value = 1, message = "El id del puesto debe ser mayor o igual a 1")
    private Long id;

    @NotBlank(message = "El nombre del puesto no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre del puesto debe tener entre 3 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del puesto debe contener letras y espacios")
    private String nombre;
}
