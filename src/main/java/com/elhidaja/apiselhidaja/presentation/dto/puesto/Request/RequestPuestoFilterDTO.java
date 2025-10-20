package com.elhidaja.apiselhidaja.presentation.dto.puesto.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id"})
public class RequestPuestoFilterDTO extends RequestObjectId {
}
