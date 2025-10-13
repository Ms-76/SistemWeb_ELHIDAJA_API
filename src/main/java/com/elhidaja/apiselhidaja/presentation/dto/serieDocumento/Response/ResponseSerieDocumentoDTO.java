package com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Response;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSerieDocumentoDTO {
    private Long id;
    private Long idSerie;
    private Long idDocumentoOperacion;
    private Long ultimoCorrelativo;
    private Boolean status;
}
