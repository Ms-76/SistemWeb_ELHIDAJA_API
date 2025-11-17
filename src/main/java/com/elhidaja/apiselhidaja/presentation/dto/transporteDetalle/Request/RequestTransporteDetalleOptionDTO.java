package com.elhidaja.apiselhidaja.presentation.dto.transporteDetalle.Request;

import lombok.Data;

import java.time.LocalDate;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.*;

@Data
public class RequestTransporteDetalleOptionDTO {

    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El la fecha inicio es obligatorio")
    private LocalDate fechaInicio;

    @NotNull(message = "El la fecha fin es obligatorio")
    private LocalDate fechaFin;

    @NotNull(message = "El tipoPasajero es obligatorio")
    @Min(value = 0, message = "El id debe ser mayor o igual a 0")
    private Long idTipoPasajero;
}
