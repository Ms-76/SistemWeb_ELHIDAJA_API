package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaTransporte.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetalleGuiaTransporteDetalleDTO {

    private Long id;
    private Long idGuiaTransporte;
    private String codigoProducto;
    private String nombreProducto;
    private Integer cantidad;
    private String unidadMedida;
    private String observacion;
    private String fechaCreacion;
    private Boolean status;
}