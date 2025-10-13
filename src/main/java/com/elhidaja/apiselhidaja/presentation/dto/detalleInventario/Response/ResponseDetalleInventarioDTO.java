package com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Response;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "detalleInventario", "exito", "mensaje", "codigo" })
public class ResponseDetalleInventarioDTO extends GlobalResponse {
    private ResponseDetalleInventarioInnerDTO detalleInventario;
}
