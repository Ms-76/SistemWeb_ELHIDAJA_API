package com.elhidaja.apiselhidaja.presentation.dto.documentoIdentidad.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequestDocumentoIdentidadIdDTO extends RequestObjectActionId {

}
