package com.elhidaja.apiselhidaja.presentation.dto.guiaTransporte.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGuiaTransporteDTO {
    private Integer idGuiaTransporte;
    private Integer idGuiaSalida;
    private String vehiculo;              
    private String chofer;  
    private String puntoPartida;
    private String nombrePuntoPartida;
    private String puntoLlegada;
    private String nombrePuntoLlegada;
    private LocalDate fechaTranslado;
    private String observacion;
    private Boolean status;
}