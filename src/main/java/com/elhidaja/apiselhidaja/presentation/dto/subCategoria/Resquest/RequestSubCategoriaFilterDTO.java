package com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Resquest;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id"})
public class RequestSubCategoriaFilterDTO extends RequestObjectId {
}
