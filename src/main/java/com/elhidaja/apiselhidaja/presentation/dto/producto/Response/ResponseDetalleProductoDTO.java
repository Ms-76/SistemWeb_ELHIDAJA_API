package com.elhidaja.apiselhidaja.presentation.dto.producto.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "producto", "exito", "mensaje", "codigo" })
public class ResponseDetalleProductoDTO extends GlobalResponse {
     private List<ResponseProductoDTO2> producto;
}
