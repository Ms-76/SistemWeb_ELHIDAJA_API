package com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "serieDocumentos", "exito", "mensaje", "codigo" })
public class ResponseSerieDocumentoAllDTO extends GlobalResponse {
    List<ResponseSerieDocumentoDTO> serieDocumentos;
}
