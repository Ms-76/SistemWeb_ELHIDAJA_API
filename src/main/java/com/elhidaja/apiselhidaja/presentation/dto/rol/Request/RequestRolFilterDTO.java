package com.elhidaja.apiselhidaja.presentation.dto.rol.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id"})
public class RequestRolFilterDTO extends RequestObjectId {
}
