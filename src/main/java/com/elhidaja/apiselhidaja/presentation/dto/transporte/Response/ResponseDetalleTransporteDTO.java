package com.elhidaja.apiselhidaja.presentation.dto.transporte.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "transporte", "exito", "mensaje", "codigo" })
public class ResponseDetalleTransporteDTO extends GlobalResponse {
    private ResponseTransporteDTO transporte;
}
