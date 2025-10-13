package com.elhidaja.apiselhidaja.presentation.dto.documentoOperacion.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "documentos_operacion", "exito", "mensaje", "codigo" })
public class ResponseDocumentoOperacionAllDTO extends GlobalResponse {
    private List<ResponseDocumentoOperacionDTO> documentos_operacion;
}
