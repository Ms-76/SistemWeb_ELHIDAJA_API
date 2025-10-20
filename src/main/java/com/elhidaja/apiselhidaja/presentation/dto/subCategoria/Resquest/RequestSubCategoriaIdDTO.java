package com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Resquest;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequestSubCategoriaIdDTO extends RequestObjectActionId {

}
