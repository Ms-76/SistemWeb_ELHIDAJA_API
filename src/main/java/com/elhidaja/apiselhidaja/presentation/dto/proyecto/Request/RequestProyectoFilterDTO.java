package com.elhidaja.apiselhidaja.presentation.dto.proyecto.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id"})
public class RequestProyectoFilterDTO extends RequestObjectId {
}
