package com.elhidaja.apiselhidaja.presentation.dto.vehiculo.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestVehiculoOptionDTO {
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El idTipoVehiculo es obligatorio")
    @Min(value = 0, message = "El idTipoVehiculo debe ser mayor o igual a 0")
    private Long idTipoVehiculo;

}
