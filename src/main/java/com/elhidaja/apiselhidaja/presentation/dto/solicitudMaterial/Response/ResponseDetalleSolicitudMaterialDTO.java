package com.elhidaja.apiselhidaja.presentation.dto.solicitudMaterial.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "solicitudMaterial", "exito", "mensaje", "codigo" })
public class ResponseDetalleSolicitudMaterialDTO extends GlobalResponse {
    private ResponseSolicitudMaterialDTO solicitudMaterial;
}
