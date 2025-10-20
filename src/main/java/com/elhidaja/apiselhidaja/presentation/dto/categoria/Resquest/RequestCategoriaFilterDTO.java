package com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
@Data
@JsonPropertyOrder({"id"})
public class RequestCategoriaFilterDTO extends RequestObjectId {
    
}
