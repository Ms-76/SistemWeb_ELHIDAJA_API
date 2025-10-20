package com.elhidaja.apiselhidaja.presentation.dto.arquitecto.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "arquitectos", "exito", "mensaje", "codigo" })
public class ResponseArquitectoAllDTO extends GlobalResponse {
    private List<ResponseArquitectoDTO> arquitectos;
}
