package com.elhidaja.apiselhidaja.presentation.dto.solicitudMaterial.Request;

import java.time.LocalDate;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.EstadoValido;
import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestSolicitudMaterialOptionDTO {

    @NotNull(message = "El id del proyecto es obligatorio")
    @Min(value = 0, message = "El id del proyecto debe ser mayor o igual a 0")
    private Long idProyecto;

    @NotNull(message = "El status es obligatorio")
    @ValidOption
    private Long status;

    @NotNull(message = "El estado es obligatorio")
    @EstadoValido
    private Long estado;

    @NotNull(message = "El id del supervisor de obra es obligatorio")
    @Min(value = 0, message = "El id del supervisor de obra debe ser mayor o igual a 0")
    private Long idSupervisor;

    @NotNull(message = "El la fecha inicio es obligatorio")
    private LocalDate fechaInicio;

    @NotNull(message = "El la fecha fin es obligatorio")
    private LocalDate fechaFin;

}
