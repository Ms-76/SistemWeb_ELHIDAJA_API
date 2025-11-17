package com.elhidaja.apiselhidaja.presentation.dto.vehiculoChoferAsignacion.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestVehiculoChoferAsignacionUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El idAsignacion es obligatorio")
    @Min(value = 1, message = "El idAsignacion debe ser mayor o igual a 1")
    private Long idAsignacion;

    @NotNull(message = "El idVehiculo es obligatorio")
    @Min(value = 1, message = "El idVehiculo debe ser mayor o igual a 1")
    private Long idVehiculo;

    @NotNull(message = "El idChofer es obligatorio")
    @Min(value = 1, message = "El idChofer debe ser mayor o igual a 1")
    private Long idChofer;
}
