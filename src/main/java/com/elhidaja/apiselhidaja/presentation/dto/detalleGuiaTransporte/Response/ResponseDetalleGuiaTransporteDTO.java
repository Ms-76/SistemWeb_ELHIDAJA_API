package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaTransporte.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "detalleGuia", "exito", "mensaje", "codigo" })
public class ResponseDetalleGuiaTransporteDTO extends GlobalResponse {
    private ResponseDetalleGuiaTransporteDetalleDTO detalleGuia;
}