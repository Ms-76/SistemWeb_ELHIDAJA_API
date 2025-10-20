package com.elhidaja.apiselhidaja.presentation.dto.tipoOperacion.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequestTipoOperacionIdDTO extends RequestObjectActionId{

}
