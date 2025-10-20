package com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequestDetalleInventarioIdDTO extends RequestObjectActionId {

}
