package com.elhidaja.apiselhidaja.presentation.dto.distrito.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "nombre", "idProvincia" })

public class RequestDistritoInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "El nombre del distrito no puede estar vacío")
    @LengthSQL(tabla = "distrito", columna = "nombre")
    @Pattern(regexp = "^[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s]+$", message = "El nombre del distrito debe contener solo letras y espacios")
    private String nombre;

    @NotNull(message = "El idProvincia es obligatorio")
    @Min(value = 1, message = "El idProvincia debe ser mayor o igual a 1")
    private Long idProvincia;
}
