package com.elhidaja.apiselhidaja.presentation.dto.proyecto.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestProyectoOptionDTO {

    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El id del arquitecto es obligatorio")
    @Min(value = 0, message = "El id del arquitecto debe ser mayor o igual a 0")
    private Long idArquitecto;

    @NotNull(message = "El id del ingeniero es obligatorio")
    @Min(value = 0, message = "El id del ingeniero debe ser mayor o igual a 0")
    private Long idIngeniero;

    @NotNull(message = "El id del maestro de obra es obligatorio")
    @Min(value = 0, message = "El id del maestro de obra debe ser mayor o igual a 0")
    private Long idMaestroObra;

    @NotNull(message = "El id del supervisor es obligatorio")
    @Min(value = 0, message = "El id del supervisor debe ser mayor o igual a 0")
    private Long idSupervisor;
}
