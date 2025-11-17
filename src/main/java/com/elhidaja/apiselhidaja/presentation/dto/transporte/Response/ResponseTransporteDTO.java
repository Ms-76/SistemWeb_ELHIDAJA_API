package com.elhidaja.apiselhidaja.presentation.dto.transporte.Response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTransporteDTO {
    private Long id;
    private String tipoTransporte;
    private String tipoOrigen;
    private String nombreOrigen;
    private String tipoDestino;
    private String nombreDestino;
    private LocalDate fechaProgramada;
    private String observaciones;
    private String placa;
    private String tipoVehiculo;
    private String nombre;
    private String licencia;
    private Boolean status;
}
