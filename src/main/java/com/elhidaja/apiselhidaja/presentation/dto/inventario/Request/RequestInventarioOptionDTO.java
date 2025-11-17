package com.elhidaja.apiselhidaja.presentation.dto.inventario.Request;

import java.time.LocalDate;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestInventarioOptionDTO {
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El id del almacen es obligatorio")
    @Min(value = 0, message = "El id del almacen debe ser mayor o igual a 0")
    private Long idAlmacen;

    @NotNull(message = "El id del supervisor es obligatorio")
    @Min(value = 0, message = "El id del supervisor debe ser mayor o igual a 0")
    private Long idSupervisor;

    @NotNull(message = "El la fecha inicio es obligatorio")
    private LocalDate fechaInicio;

    @NotNull(message = "El la fecha fin es obligatorio")
    private LocalDate fechaFin;
}
