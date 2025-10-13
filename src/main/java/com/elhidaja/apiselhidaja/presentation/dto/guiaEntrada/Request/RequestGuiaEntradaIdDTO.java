package com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
@Data
public class RequestGuiaEntradaIdDTO {
    @NotNull(message = "El idGuiaEntrada es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor o igual a 1")
    private Long id;
}
