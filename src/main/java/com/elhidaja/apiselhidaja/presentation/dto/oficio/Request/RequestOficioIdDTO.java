package com.elhidaja.apiselhidaja.presentation.dto.oficio.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequestOficioIdDTO extends RequestObjectActionId {

}
