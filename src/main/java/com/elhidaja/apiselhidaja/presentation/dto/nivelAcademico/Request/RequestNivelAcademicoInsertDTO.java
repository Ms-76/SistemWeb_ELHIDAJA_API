package com.elhidaja.apiselhidaja.presentation.dto.nivelAcademico.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "nombre" })
public class RequestNivelAcademicoInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "El nombre del nivel académico no puede estar vacío")
    @LengthSQL(tabla = "nivel_academico", columna = "nombre")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del nivel académico debe contener solo letras y espacios")
    private String nombre;
}
