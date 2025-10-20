package com.elhidaja.apiselhidaja.presentation.dto.puesto.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequestPuestoIdDTO extends RequestObjectActionId{

}
