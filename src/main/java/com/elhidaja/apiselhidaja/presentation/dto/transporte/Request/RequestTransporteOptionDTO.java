package com.elhidaja.apiselhidaja.presentation.dto.transporte.Request;

import lombok.Data;

import java.time.LocalDate;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class RequestTransporteOptionDTO {
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El id del chofer es obligatorio")
    @Min(value = 0, message = "El id del chofer debe ser mayor o igual a 0")
    private Long idChofer;

    @NotNull(message = "El id del tipo de transporte es obligatorio")
    @Min(value = 0, message = "El id del tipo de transporte debe ser mayor o igual a 0")
    private Long idTipoTransporte;

    @NotNull(message = "El id del tipo de origen es obligatorio")
    @Min(value = 0, message = "El id del tipo de origen debe ser mayor o igual a 0")
    private Long idTipoOrigen;

    @NotNull(message = "El id del tipo de destino es obligatorio")
    @Min(value = 0, message = "El id del tipo de destino debe ser mayor o igual a 0")
    private Long idTipoDestino;

    @NotNull(message = "El la fecha inicio es obligatorio")
    private LocalDate fechaInicio;

    @NotNull(message = "El la fecha fin es obligatorio")
    private LocalDate fechaFin;
}
