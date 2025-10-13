package com.elhidaja.apiselhidaja.presentation.dto.inventario.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestInventarioIdDTO {
    @NotNull(message = "El id del inventario es obligatorio")
    @Min(value = 1, message = "El id del inventario debe ser mayor o igual a 1")
    private Long id;
}
