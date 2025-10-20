package com.elhidaja.apiselhidaja.presentation.dto.estante.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequestEstanteIdDTO extends RequestObjectActionId {
    
}
