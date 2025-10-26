package com.elhidaja.apiselhidaja.presentation.dto.tipoOperacion.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin","id", "nombre", "abreviatura" })
public class RequestTipoOperacionUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El id es obligatorio")
    @Min(value = 1, message = "El id de la operación debe ser mayor o igual a 1")
    private Long id;

    @NotBlank(message = "El nombre de la operación no puede estar vacío")
    @LengthSQL(tabla = "tipo_operacion", columna = "nombre")
    @Pattern(regexp = "^[^\\d]*$", message = "El nombre no puede contener números")
    private String nombre;

    @NotBlank(message = "La abreviatura no puede estar vacía")
    @LengthSQL(tabla = "tipo_operacion", columna = "abreviatura")
    @Pattern(regexp = "^[^\\d]*$", message = "La abreviatura no puede contener números")
    private String abreviatura;
}
