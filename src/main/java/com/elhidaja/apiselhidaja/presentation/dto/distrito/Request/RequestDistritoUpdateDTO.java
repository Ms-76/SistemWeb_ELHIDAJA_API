package com.elhidaja.apiselhidaja.presentation.dto.distrito.Request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin","id", "nombre", "idProvincia" })
public class RequestDistritoUpdateDTO {

    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;
    @NotNull(message = "El idDistrito es obligatorio")
    @Min(value = 1, message = "El id del distrito debe ser mayor o igual a 1")
    private Long id;

    @NotBlank(message = "El nombre del distrito no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre del distrito debe tener entre 3 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s]+$", message = "El nombre del distrito debe contener solo letras y espacios")
    private String nombre;

    @NotNull(message = "El idProvincia es obligatorio")
    @Min(value = 1, message = "El idProvincia debe ser mayor o igual a 1")
    private Long idProvincia;
}
