package com.elhidaja.apiselhidaja.presentation.dto.tipoVehiculo.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTipoVehiculoDTO {
    private Long id;
    private String nombre;
    private Boolean status;
}
