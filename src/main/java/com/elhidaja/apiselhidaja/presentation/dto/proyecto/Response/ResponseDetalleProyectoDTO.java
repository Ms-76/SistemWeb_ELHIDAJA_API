package com.elhidaja.apiselhidaja.presentation.dto.proyecto.Response;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "proyecto", "exito", "mensaje",  "codigo" })
public class ResponseDetalleProyectoDTO extends GlobalResponse {
    private ResponseProyectoDTO proyecto;
}
