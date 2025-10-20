package com.elhidaja.apiselhidaja.presentation.dto.rol.Request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "nombre" })

public class RequestRolInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "El nombre del rol no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del rol debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del rol debe contener solo letras y espacios")
    private String nombre;
}
