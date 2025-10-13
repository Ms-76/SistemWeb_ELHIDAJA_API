package com.elhidaja.apiselhidaja.presentation.dto.inventario.Response;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "inventarios", "exito", "mensaje", "codigo" })
public class ResponseInventarioAllDTO extends GlobalResponse{
      private List<ResponseInventarioDTO> inventarios;
}
