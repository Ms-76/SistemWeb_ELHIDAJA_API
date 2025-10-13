package com.elhidaja.apiselhidaja.presentation.dto.departamento.Response;

import java.util.List;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "departamentos", "exito", "mensaje", "codigo" })
public class ResponseDepartamentoAllDTO  extends GlobalResponse{
     private List<ResponseDepartamentoDTO> departamentos;
}
