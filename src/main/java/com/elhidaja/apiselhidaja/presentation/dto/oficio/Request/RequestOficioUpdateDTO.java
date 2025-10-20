package com.elhidaja.apiselhidaja.presentation.dto.oficio.Request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin","id", "nombre" })
public class RequestOficioUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El idOficio es obligatorio")
    @Min(value = 1, message = "El id del oficio debe ser mayor o igual a 1")
    private Long id;

    @NotBlank(message = "El nombre del oficio no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del oficio debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del oficio debe contener solo letras y espacios")
    private String nombre;
}
