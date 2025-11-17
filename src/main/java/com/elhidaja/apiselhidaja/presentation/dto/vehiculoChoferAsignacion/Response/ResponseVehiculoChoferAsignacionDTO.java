package com.elhidaja.apiselhidaja.presentation.dto.vehiculoChoferAsignacion.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVehiculoChoferAsignacionDTO {
    private Long id;
    private Long idVehiculo;
    private String placa;
    private Long idChofer;
    private String documento;
    private String numeroDocumento;
    private String nombre;
    private String licencia;
    private String email;
    private String telefono;
    private Boolean status;
}
