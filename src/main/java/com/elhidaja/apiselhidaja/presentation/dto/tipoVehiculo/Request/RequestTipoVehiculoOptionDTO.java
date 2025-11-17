package com.elhidaja.apiselhidaja.presentation.dto.tipoVehiculo.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

@Data
public class RequestTipoVehiculoOptionDTO {
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;
}
