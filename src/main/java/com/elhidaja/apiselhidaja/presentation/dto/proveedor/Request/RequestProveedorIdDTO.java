package com.elhidaja.apiselhidaja.presentation.dto.proveedor.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequestProveedorIdDTO extends RequestObjectActionId {

}
