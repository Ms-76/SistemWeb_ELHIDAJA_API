package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Response;
import lombok.Getter;
import lombok.Setter;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "detalleGuiaEntradas", "exito", "mensaje",  "codigo" })
public class ResponseDetalleGuiaEntradaAllDTO  extends GlobalResponse{
     List<ResponseDetalleGuiaEntradaDTOInner> detalleGuiaEntradas;
}
