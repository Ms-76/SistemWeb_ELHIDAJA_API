package com.elhidaja.apiselhidaja.presentation.dto.documentoIdentidad.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "documentoIdentidad", "exito", "mensaje", "codigo" })
public class ResponseDetalleDocumentoIdentidadDTO extends GlobalResponse{
      private ResponseDocumentoIdentidadDTO documentoIdentidad;
}
