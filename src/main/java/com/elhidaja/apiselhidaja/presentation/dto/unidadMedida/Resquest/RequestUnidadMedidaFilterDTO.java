package com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest;
import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id"})
public class RequestUnidadMedidaFilterDTO extends RequestObjectId {
}
