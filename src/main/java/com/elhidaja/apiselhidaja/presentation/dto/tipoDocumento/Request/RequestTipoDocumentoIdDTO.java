package com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequestTipoDocumentoIdDTO extends RequestObjectActionId {

}
