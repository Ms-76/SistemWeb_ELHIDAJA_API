package com.elhidaja.apiselhidaja.presentation.dto.transporte.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "transportes", "exito", "mensaje", "codigo" })
public class ResponseTransporteAllDTO  extends GlobalResponse{
        private List<ResponseTransporteDTO> transportes;
}
