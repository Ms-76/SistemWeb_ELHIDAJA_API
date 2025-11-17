package com.elhidaja.apiselhidaja.presentation.dto.transporteDetalle.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTransporteDetalleDTO {
    private Long idDetalle;
    private Long idTransporte;
    private String tipoTransporte;
    private String tipoPasajero;
    private String idPasajero;
    private String nombre;
    private Double costoPasaje;
    private Boolean bulto;
    private Double costoBulto;
    private Boolean status;
}
