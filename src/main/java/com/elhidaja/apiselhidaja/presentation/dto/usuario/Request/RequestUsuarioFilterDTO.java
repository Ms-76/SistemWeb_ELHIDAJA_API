package com.elhidaja.apiselhidaja.presentation.dto.usuario.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id"})
public class RequestUsuarioFilterDTO extends RequestObjectId {
}
