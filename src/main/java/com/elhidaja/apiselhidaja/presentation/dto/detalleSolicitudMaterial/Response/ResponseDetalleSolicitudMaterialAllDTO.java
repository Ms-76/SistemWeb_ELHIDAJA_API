package com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Response;
import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "detalleSolicitudesMaterial", "exito", "mensaje", "codigo" })
public class ResponseDetalleSolicitudMaterialAllDTO extends GlobalResponse{
    List<ResponseDetalleSolicitudMaterialItemDTO> detalleSolicitudesMaterial;
}
