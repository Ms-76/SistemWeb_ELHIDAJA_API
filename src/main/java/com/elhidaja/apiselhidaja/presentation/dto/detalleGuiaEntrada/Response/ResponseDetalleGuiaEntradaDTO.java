package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "detalleGuiaEntrada", "exito", "mensaje",  "codigo" })
public class ResponseDetalleGuiaEntradaDTO extends GlobalResponse {
      private ResponseDetalleGuiaEntradaDTOInner detalleGuiaEntrada;
}
