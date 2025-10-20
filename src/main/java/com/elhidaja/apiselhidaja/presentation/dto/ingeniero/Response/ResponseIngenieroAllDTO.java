package com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "ingenieros", "exito", "mensaje", "codigo" })
public class ResponseIngenieroAllDTO extends GlobalResponse {
    private List<ResponseIngenieroDTO> ingenieros;
}
