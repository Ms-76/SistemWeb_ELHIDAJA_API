package com.elhidaja.apiselhidaja.presentation.dto.vehiculo.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequestVehiculoIdDTO extends RequestObjectActionId  {
    
}
