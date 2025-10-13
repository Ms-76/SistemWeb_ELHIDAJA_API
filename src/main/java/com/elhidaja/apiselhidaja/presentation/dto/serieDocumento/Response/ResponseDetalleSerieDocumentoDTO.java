package com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "serieDocumento", "exito", "mensaje", "codigo" })
public class ResponseDetalleSerieDocumentoDTO extends GlobalResponse{
    private ResponseSerieDocumentoDTO serieDocumento;
}
