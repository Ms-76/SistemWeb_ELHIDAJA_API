package com.elhidaja.apiselhidaja.presentation.dto.proveedor.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class RequestProveedorIdDTO {
    @NotNull(message = "El idProveedor es obligatorio")
    @Min(value = 1, message = "El id del proveedor debe ser mayor o igual a 1")
    private Long id;
}
