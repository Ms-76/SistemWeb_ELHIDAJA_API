package com.elhidaja.apiselhidaja.presentation.dto.documentoIdentidad.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonPropertyOrder({ "documentosIdentidad", "exito", "mensaje", "codigo" })
public class ResponseDocumentoIdentidadAllDTO extends GlobalResponse {
        List<ResponseDocumentoIdentidadDTO> documentosIdentidad;
}
