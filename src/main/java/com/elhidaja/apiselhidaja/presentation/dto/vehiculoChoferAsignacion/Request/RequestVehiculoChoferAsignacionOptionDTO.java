package com.elhidaja.apiselhidaja.presentation.dto.vehiculoChoferAsignacion.Request;

import lombok.Data;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.*;

@Data
public class RequestVehiculoChoferAsignacionOptionDTO {
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El idChofer es obligatorio")
    @Min(value = 0, message = "El idChofer debe ser mayor o igual a 0")
    private Long idChofer;
}
