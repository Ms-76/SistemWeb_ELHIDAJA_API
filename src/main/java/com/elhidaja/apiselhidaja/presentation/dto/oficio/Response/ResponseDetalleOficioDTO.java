package com.elhidaja.apiselhidaja.presentation.dto.oficio.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "oficio", "exito", "mensaje", "codigo" })
public class ResponseDetalleOficioDTO extends GlobalResponse {
    private ResponseOficioDTO oficio;
}
