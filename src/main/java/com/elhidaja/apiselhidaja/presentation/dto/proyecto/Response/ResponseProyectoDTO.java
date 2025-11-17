package com.elhidaja.apiselhidaja.presentation.dto.proyecto.Response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProyectoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String nombreArquitecto;
    private String nombreIngeniero;
    private String nombreMaestroObra;
    private String nombreSupervisor;
    private Boolean status;
}
