package com.elhidaja.apiselhidaja.presentation.dto.serie.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "serie", "exito", "mensaje", "codigo" })
public class ResponseDetalleSerieDTO extends GlobalResponse {
    private ResponseSerieDTO serie;
}
