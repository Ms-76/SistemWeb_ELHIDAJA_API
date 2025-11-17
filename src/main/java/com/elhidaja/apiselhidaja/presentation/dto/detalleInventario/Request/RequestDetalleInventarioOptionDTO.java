package com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Request;

import java.time.LocalDate;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestDetalleInventarioOptionDTO {

    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El id del inventario es obligatorio")
    @Min(value = 0, message = "El id del inventario debe ser mayor o igual a 0")
    private Long idInventario;

    @NotNull(message = "El id del producto es obligatorio")
    @Min(value = 0, message = "El id del producto debe ser mayor o igual a 0")
    private Long idProducto;

    @NotNull(message = "El id del inventariador es obligatorio")
    @Min(value = 0, message = "El id del inventariador debe ser mayor o igual a 0")
    private Long idInventariador;

    @NotNull(message = "El la fecha inicio es obligatorio")
    private LocalDate fechaInicio;

    @NotNull(message = "El la fecha fin es obligatorio")
    private LocalDate fechaFin;

}
