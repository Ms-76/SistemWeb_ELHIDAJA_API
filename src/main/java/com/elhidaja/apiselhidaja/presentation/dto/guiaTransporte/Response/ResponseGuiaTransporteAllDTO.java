package com.elhidaja.apiselhidaja.presentation.dto.guiaTransporte.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "guiasTransporte", "exito", "mensaje", "codigo" })
public class ResponseGuiaTransporteAllDTO extends GlobalResponse {
    private List<ResponseGuiaTransporteDTO> guiasTransporte;
}