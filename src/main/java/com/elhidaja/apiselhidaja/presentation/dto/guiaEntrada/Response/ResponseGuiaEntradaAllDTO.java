package com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "guiasEntrada", "exito", "mensaje", "codigo" })
public class ResponseGuiaEntradaAllDTO extends GlobalResponse {
    List<ResponseGuiaEntradaDTO> guiasEntrada;
}
