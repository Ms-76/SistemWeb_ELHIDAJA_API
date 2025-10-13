package com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Request;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class RequestDetalleInventarioInsertDTO {
    @NotNull(message = "El idInventario es obligatorio")
    @Min(value = 1, message = "El id del Inventario debe ser mayor a 0")
    private Long idInventario;

    @NotNull(message = "El id del Producto es obligatorio")
    @Min(value = 1, message = "El id del Producto debe ser mayor a 0")
    private Long idProducto;

    @NotNull(message = "El id del Usuario es obligatorio")
    @Min(value = 1, message = "El id del Usuario debe ser mayor a 0")
    private Long idUsuario;
}
