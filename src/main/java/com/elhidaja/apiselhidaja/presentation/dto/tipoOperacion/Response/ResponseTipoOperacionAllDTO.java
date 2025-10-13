package com.elhidaja.apiselhidaja.presentation.dto.tipoOperacion.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "tipoOperaciones", "exito", "mensaje",  "codigo" })
public class ResponseTipoOperacionAllDTO extends GlobalResponse {
      List<ResponseTipoOperacionDTO> tipoOperaciones;
}
