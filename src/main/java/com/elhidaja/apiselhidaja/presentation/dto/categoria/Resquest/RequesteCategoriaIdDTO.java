package com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequesteCategoriaIdDTO extends RequestObjectActionId {

}
