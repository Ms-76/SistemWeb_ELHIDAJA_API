package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class RequestDetalleGuiaEntradaIdDTO {
    @NotNull(message = "El idDetalleGuiaEntrada es obligatorio")
    @Min(value = 1, message = "El idDetalleGuiaEntrada debe ser mayor o igual a 1")
    private Integer idDetalleGuiaEntrada;
}
