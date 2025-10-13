package com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "tiposDocumento", "exito", "mensaje", "codigo" })
public class ResponseTipoDocumentoAllDTO extends GlobalResponse {
    private List<ResponseTipoDocumentoDTO> tiposDocumento;
}
