package com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Response;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "detalleSolicitudMaterial", "exito", "mensaje", "codigo" })
public class ResponseDetalleSolicitudMaterialDTO extends GlobalResponse {
        private ResponseDetalleSolicitudMaterialItemDTO detalleSolicitudMaterial;
}
