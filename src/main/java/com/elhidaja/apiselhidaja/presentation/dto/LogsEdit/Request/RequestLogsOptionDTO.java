package com.elhidaja.apiselhidaja.presentation.dto.LogsEdit.Request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@JsonPropertyOrder({ "idUsuario", "fechaInicio", "fechaFin" })
public class RequestLogsOptionDTO {

    @NotNull(message = "El id del usuario es obligatorio")
    @Min(value = 0, message = "ID del usuario debe ser mayor o igual a 0")
    private Long idUsuario;

    @NotNull(message = "El la fecha inicio es obligatorio")
    private LocalDate fechaInicio;

    @NotNull(message = "El la fecha fin es obligatorio")
    private LocalDate fechaFin;

    @NotNull(message = "El tipo de acción es obligatorio")
    @Pattern(regexp = "ALL|INSERT|UPDATE|ACTIVATE|DEACTIVATE", message = "Tipo de acción inválido. Valores permitidos: ALL, INSERT, UPDATE, ACTIVATE, DEACTIVATE")
    private String tipoAccion;

}
