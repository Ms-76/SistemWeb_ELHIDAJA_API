package com.elhidaja.apiselhidaja.presentation.dto.transporteDetalle.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "detalle", "exito", "mensaje", "codigo" })
public class ResponseDetalleTransporteDetalleDTO extends GlobalResponse {
    private ResponseTransporteDetalleDTO detalle;
}
