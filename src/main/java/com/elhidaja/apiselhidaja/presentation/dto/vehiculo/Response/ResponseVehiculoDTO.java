package com.elhidaja.apiselhidaja.presentation.dto.vehiculo.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVehiculoDTO {
    private Long id;
    private String placa;
    private String color;
    private Integer cantidadAsientos;
    private Integer anio;
    private String tipoVehiculo;
    private Boolean status;
}
