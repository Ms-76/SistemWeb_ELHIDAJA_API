package com.elhidaja.apiselhidaja.presentation.dto.solicitudMaterial.Response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSolicitudMaterialDTO {
    private Long id;
    private String nombreProyecto;
    private String nombreSupervisor;
    private String nombreSolicitud;
    private String descripcion;
    private Integer estado;
    private LocalDateTime fechaSolicitud;
    private Boolean status;
}
