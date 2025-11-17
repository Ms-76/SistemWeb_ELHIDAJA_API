package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaTransporte.Request;

import lombok.Data;
import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@JsonPropertyOrder({ "idLogin", "idDetalleGuia" })
public class RequestDetalleGuiaTransporteIdDTO extends RequestObjectActionId {
    @Override
    @JsonProperty("idDetalleGuia")
    public Long getId() {
        return super.getId();
    }

    @Override
    @JsonProperty("idDetalleGuia")
    public void setId(Long idDetalleGuia) {
        super.setId(idDetalleGuia);
    }
}