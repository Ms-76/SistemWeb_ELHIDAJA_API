package com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Response;

import lombok.Getter;
import lombok.Setter;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "detalleInventarios", "exito", "mensaje", "codigo" })
public class ResponseDetalleInventarioAllDTO extends GlobalResponse {
    List<ResponseDetalleInventarioInnerDTO> detalleInventarios;
}
