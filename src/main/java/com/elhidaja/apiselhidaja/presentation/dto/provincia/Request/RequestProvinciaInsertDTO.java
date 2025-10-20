package com.elhidaja.apiselhidaja.presentation.dto.provincia.Request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "nombre", "idDepartamento" })
public class RequestProvinciaInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "El nombre de la provincia no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre de la provincia debe tener entre 3 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre de la provincia debe contener solo letras y espacios")
    private String nombre;

    @NotNull(message = "El id del departamento es obligatorio")
    @Min(value = 1, message = "El id del departamento debe ser mayor o igual a 1")
    private Long idDepartamento;
}
