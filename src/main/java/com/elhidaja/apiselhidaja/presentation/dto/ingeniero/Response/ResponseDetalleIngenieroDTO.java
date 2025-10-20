package com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "ingeniero", "exito", "mensaje", "codigo" })
public class ResponseDetalleIngenieroDTO extends GlobalResponse {
    private ResponseIngenieroDTO ingeniero;
}
