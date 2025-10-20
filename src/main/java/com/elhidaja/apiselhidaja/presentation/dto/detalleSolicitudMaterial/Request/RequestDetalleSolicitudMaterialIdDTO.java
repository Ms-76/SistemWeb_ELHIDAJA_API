package com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


import lombok.Data;

@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequestDetalleSolicitudMaterialIdDTO extends RequestObjectActionId{

}
