package com.elhidaja.apiselhidaja.presentation.dto.almacen.Request;
import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"id"})
public class RequestAlmacenFilterDTO extends RequestObjectId{
    
}
