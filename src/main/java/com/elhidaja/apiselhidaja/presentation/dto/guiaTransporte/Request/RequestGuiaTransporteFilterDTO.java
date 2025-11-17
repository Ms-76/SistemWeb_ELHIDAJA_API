package com.elhidaja.apiselhidaja.presentation.dto.guiaTransporte.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id"})
public class RequestGuiaTransporteFilterDTO extends RequestObjectId {
}