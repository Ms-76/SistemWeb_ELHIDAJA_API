package com.elhidaja.apiselhidaja.presentation.dto.transporteDetalle.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "detalles", "exito", "mensaje", "codigo" })
public class ResponseTransporteDetalleAllDTO extends GlobalResponse {
    private List<ResponseTransporteDetalleDTO> detalles;
}
