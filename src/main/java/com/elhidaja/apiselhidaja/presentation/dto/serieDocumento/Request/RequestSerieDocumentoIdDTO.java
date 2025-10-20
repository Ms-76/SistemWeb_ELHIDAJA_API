package com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequestSerieDocumentoIdDTO  extends RequestObjectActionId {

}
