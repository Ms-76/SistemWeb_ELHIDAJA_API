package com.elhidaja.apiselhidaja.presentation.dto.LogsEdit.Request;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonPropertyOrder({ "idUsuario", "fechaInicio", "fechaFin" })
public class RequestLogsOptionDTO {

    @NotNull(message = "El id del usuario es obligatorio")
    private Long idUsuario;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFin;
}
