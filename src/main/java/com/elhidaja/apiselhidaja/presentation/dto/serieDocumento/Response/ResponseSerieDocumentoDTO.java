package com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Response;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSerieDocumentoDTO {
    private Long id;
    private String serie;
    private String tipoDocumento;
    private String tipoOperacion;
    private Long codigoSunat;
    private Long codigoInterno;
    private Long ultimoCorrelativo;
    private Boolean status;
}
