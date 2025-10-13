package com.elhidaja.apiselhidaja.presentation.dto.documentoOperacion.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "documento_operacion", "exito", "mensaje", "codigo" })
public class ResponseDetalleDocumentoOperacionDTO extends GlobalResponse {
    private ResponseDocumentoOperacionDTO documento_operacion;
}
