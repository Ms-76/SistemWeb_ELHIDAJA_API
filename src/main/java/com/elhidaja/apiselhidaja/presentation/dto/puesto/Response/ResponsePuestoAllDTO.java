package com.elhidaja.apiselhidaja.presentation.dto.puesto.Response;
import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonPropertyOrder({ "puestos", "exito", "mensaje",  "codigo" })
public class ResponsePuestoAllDTO extends GlobalResponse {
        List<ResponsePuestoDTO> puestos;
}
