package com.elhidaja.apiselhidaja.presentation.dto.arquitecto.Request;

import lombok.Data;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequestArquitectoIdDTO extends RequestObjectActionId{
}
