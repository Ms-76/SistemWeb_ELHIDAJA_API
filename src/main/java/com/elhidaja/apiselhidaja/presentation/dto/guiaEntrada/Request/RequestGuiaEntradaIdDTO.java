package com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;
@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequestGuiaEntradaIdDTO extends RequestObjectActionId{

}
