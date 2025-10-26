package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaSalida.Response;
import java.time.LocalDateTime;
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
    private LocalDateTime fechaVencimientoProducto;
    private String producto;
    private Long cantidad;
    private String unidadMedida;
    private String observacion;
    private Boolean status;
}
