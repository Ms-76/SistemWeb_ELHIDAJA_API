package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaTransporte.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "detallesGuia", "exito", "mensaje", "codigo" })
public class ResponseDetalleGuiaTransporteAllDTO extends GlobalResponse {
    private List<ResponseDetalleGuiaTransporteDTO> detallesGuia;
}