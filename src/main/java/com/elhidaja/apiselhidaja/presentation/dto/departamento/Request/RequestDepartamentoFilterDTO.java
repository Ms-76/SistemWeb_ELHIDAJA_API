package com.elhidaja.apiselhidaja.presentation.dto.departamento.Request;
import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"id"})
public class RequestDepartamentoFilterDTO  extends RequestObjectId{
    
}
