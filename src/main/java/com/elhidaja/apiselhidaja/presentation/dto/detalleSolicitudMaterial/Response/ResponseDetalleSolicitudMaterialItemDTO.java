package com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Response;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetalleSolicitudMaterialItemDTO {
    private Long id;
    private Long idSolicitudMaterial;
    private String producto;
    private Integer cantidad;
    private String observacion;
    private String nombreSolicitud;
    private String estado;
    private LocalDateTime FechaSolicitud;
    private Boolean status;
}
