package com.elhidaja.apiselhidaja.presentation.dto.provincia.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "provincia", "exito", "mensaje",  "codigo" })
public class ResponseDetalleProvinciaDTO extends GlobalResponse{
     private ResponseProvinciaDTO provincia;
}
