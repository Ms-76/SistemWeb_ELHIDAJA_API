package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Response;

import lombok.Data;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetalleGuiaEntradaDTOInner {

    private Long idDetalleGuiaEntrada;
    private Long idGuiaEntrada;
    private String tipoOperacion;
    private String tipoDocumento;
    private Long codigoSunat;
    private Long codigoInterno;
    private String serie;
    private Long ultimoCorrelativo;
    private LocalDateTime fechaVencimientoProducto;
    private String producto;
    private Long cantidad;
    private String unidadMedida;
    private String observacion;
    private Boolean status;
}