package com.elhidaja.apiselhidaja.presentation.dto.departamento.Response;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "departamento", "exito", "mensaje", "codigo" })
public class ResponseDetalleDepartamentoDTO extends GlobalResponse {
        private ResponseDepartamentoDTO departamento;
}
