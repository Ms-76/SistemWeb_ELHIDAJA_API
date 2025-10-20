package com.elhidaja.apiselhidaja.presentation.dto.usuario.Request;

import lombok.Data;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonPropertyOrder({ "idLogin", "idUsuario" })
public class RequestUsuarioIdDTO extends RequestObjectActionId {
    @Override
    @JsonProperty("idUsuario") 
    public Long getId() {
        return super.getId();
    }

    @Override
    @JsonProperty("idUsuario") 
    public void setId(Long idProducto) {
        super.setId(idProducto);
    }

}
