package com.elhidaja.apiselhidaja.presentation.dto.tipoVehiculo.Request;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonPropertyOrder({ "nombre" })
public class RequestTipoVehiculoInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "El nombre no puede estar vacío")
    @LengthSQL(tabla = "tipo_vehiculo", columna = "nombre")
    private String nombre;
}
