package com.elhidaja.apiselhidaja.presentation.dto.vehiculo.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "vehiculos", "exito", "mensaje", "codigo" })
public class ResponseVehiculoAllDTO extends GlobalResponse {
    private List<ResponseVehiculoDTO> vehiculos;
}
