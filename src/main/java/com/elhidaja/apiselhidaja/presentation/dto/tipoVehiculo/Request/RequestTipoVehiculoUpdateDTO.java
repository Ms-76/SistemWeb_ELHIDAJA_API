package com.elhidaja.apiselhidaja.presentation.dto.tipoVehiculo.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonPropertyOrder({ "id", "nombre", "status" })
public class RequestTipoVehiculoUpdateDTO {
    
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El id es obligatorio")
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @LengthSQL(tabla = "tipo_vehiculo", columna = "nombre")
    private String nombre;

    @NotNull(message = "El status no puede ser nulo")
    private Boolean status;
}
