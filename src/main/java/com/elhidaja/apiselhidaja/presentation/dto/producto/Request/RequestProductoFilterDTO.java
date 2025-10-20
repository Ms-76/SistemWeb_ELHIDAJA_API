package com.elhidaja.apiselhidaja.presentation.dto.producto.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonPropertyOrder({ "id" , "idAlmacen" })
public class RequestProductoFilterDTO extends RequestObjectId {
    @NotNull(message = "El ID del almacen es obligatorio")
    @Min(value = 0, message = "El ID del almacen debe ser mayor que cero")
    private Long idAlmacen;
}
