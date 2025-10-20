package com.elhidaja.apiselhidaja.presentation.dto.rol.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequestRolIdDTO extends RequestObjectActionId {

}
