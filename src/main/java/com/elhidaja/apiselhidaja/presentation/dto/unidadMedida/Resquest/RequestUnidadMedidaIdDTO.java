package com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequestUnidadMedidaIdDTO  extends RequestObjectActionId {


}
