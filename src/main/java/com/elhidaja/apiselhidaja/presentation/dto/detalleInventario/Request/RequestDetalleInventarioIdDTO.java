package com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestDetalleInventarioIdDTO {
    @NotNull(message = "El idDetalleInventario es obligatorio")
    @Min(value = 1, message = "El idDetalleInventario debe ser mayor o igual a 1")
    private Long idDetalleInventario;
}
