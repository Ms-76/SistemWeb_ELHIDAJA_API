package com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "tipoDocumento", "exito", "mensaje", "codigo" })
public class ResponseDetalleTipoDocumentoDTO extends GlobalResponse {
    private ResponseTipoDocumentoDTO tipoDocumento;
}
