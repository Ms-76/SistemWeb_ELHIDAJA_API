package com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Request;

import lombok.Data;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequestIngenieroIdDTO extends RequestObjectActionId{
}
