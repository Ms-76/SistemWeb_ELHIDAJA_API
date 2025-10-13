package com.elhidaja.apiselhidaja.presentation.dto.distrito.Request;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDistritoInsertDTO {
    @NotBlank(message = "El nombre del distrito no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre del distrito debe tener entre 3 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s]+$", message = "El nombre del distrito debe contener solo letras y espacios")
    private String nombre;

    @NotNull(message = "El idProvincia es obligatorio")
    @Min(value = 1, message = "El idProvincia debe ser mayor o igual a 1")
    private Long idProvincia;
}
