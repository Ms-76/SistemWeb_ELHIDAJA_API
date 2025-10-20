package com.elhidaja.apiselhidaja.presentation.dto.arquitecto.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "arquitecto", "exito", "mensaje", "codigo" })
public class ResponseDetalleArquitectoDTO extends GlobalResponse {
    private ResponseArquitectoDTO arquitecto;
}
