package com.elhidaja.apiselhidaja.presentation.dto.solicitudMaterial.Response;
import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "solicitudesMaterial", "exito", "mensaje", "codigo" })
public class ResponseSolicitudMaterialAllDTO extends GlobalResponse{
        List<ResponseSolicitudMaterialDTO> solicitudesMaterial;
}
