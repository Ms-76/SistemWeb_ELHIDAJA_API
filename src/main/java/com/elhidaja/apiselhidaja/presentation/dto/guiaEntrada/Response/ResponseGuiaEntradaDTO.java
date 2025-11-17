package com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGuiaEntradaDTO {
    private Long id;
    private String tipoOperacion;
    private String tipoDocumento;
    private Long codigoSunat;
    private Long codigoInterno;
    private String serie;
    private Long ultimoCorrelativo;
    private String proveedor;
    private String trabajador;
    private LocalDateTime fechaEntrada;
    private String descripcion;
}
