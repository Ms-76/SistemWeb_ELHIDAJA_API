package com.elhidaja.apiselhidaja.presentation.dto.maestroobra.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "maestrosObra", "exito", "mensaje", "codigo" })
public class ResponseMaestroObraAllDTO extends GlobalResponse {
    private List<ResponseMaestroObraDTO> maestrosObra;
}
