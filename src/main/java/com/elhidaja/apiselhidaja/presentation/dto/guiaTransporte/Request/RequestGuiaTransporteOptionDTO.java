package com.elhidaja.apiselhidaja.presentation.dto.guiaTransporte.Request;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

@Data
public class RequestGuiaTransporteOptionDTO {

    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El idGuiaSalida es obligatorio")
    @Min(value = 0, message = "El idGuiaSalida debe ser mayor o igual a 0")
    private Integer idChofer;

    @NotNull(message = "El idAsignacion es obligatorio")
    @Min(value = 0, message = "El idAsignacion debe ser mayor o igual a 0")
    private Integer idVehiculo;

    @NotNull(message = "El punto partida es obligatorio")
    @Min(value = 0, message = "El punto partida debe ser mayor o igual a 0")
    private Integer puntoPartida;

    @NotNull(message = "El punto llegada es obligatorio")
    @Min(value = 0, message = "El punto legada debe ser mayor o igual a 0")
    private Integer puntoLlegada;

    @NotNull(message = "El la fecha inicio es obligatorio")
    private LocalDate fechaInicio;

    @NotNull(message = "El la fecha fin es obligatorio")
    private LocalDate fechaFin;
}