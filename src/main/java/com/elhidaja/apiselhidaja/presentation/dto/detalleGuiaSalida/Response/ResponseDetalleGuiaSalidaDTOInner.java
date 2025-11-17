package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaSalida.Response;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDetalleGuiaSalidaDTOInner {
    private Long idDetalleGuiaSalida;
    private Long idGuiaSalida;
    private String tipoOperacion;
    private String tipoDocumento;
    private Long codigoSunat;
    private Long codigoInterno;
    private String serie;
    private Long ultimoCorrelativo;
    private String tipoDestino;
    private String producto;
    private Long cantidad;
    private String unidadMedida;
    private String observacion;
    private Boolean status;
}
