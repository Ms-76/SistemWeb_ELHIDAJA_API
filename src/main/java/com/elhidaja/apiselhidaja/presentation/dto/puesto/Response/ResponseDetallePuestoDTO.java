package com.elhidaja.apiselhidaja.presentation.dto.puesto.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "puesto", "exito", "mensaje", "codigo" })
public class ResponseDetallePuestoDTO extends GlobalResponse {
    private ResponsePuestoDTO puesto;
}
