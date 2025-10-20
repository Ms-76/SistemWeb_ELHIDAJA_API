package com.elhidaja.apiselhidaja.presentation.dto.estante.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id"})
public class RequestEstanteFilterDTO extends RequestObjectId {
}
