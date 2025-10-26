package com.elhidaja.apiselhidaja.presentation.dto.producto.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@JsonPropertyOrder({ "idLogin", "idProducto","idAlmacen" })
public class RequestProductoIdDTO extends RequestObjectActionId{
    
    @Override
    @JsonProperty("idProducto") 
    public Long getId() {
        return super.getId();
    }

    @Override
    @JsonProperty("idProducto") 
    public void setId(Long idProducto) {
        super.setId(idProducto);
    }
    @NotNull(message = "El ID del almacen es obligatorio")
    @Min(value = 0, message = "El ID del almacen debe ser mayor que cero")
    private Long idAlmacen;
    
}
