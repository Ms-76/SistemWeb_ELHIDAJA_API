package com.elhidaja.apiselhidaja.presentation.dto.provincia.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "id", "nombre", "idDepartamento" })
public class RequestProvinciaUpdateDTO {
    
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El id de la provincia es obligatorio")
    @Min(value = 1, message = "El id de la provincia debe ser mayor o igual a 1")
    private Long id;

    @NotBlank(message = "El nombre de la provincia no puede estar vacío")
    @LengthSQL(tabla = "provincia", columna = "nombre")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre de la provincia debe contener solo letras y espacios")
    private String nombre;

    @NotNull(message = "El id del departamento es obligatorio")
    @Min(value = 1, message = "El id del departamento debe ser mayor o igual a 1")
    private Long idDepartamento;
}
