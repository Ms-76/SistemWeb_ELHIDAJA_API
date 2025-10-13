package com.elhidaja.apiselhidaja.presentation.dto.distrito.Response;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "distrito", "exito", "mensaje", "codigo" })
public class ResponseDetalleDistritoDTO extends GlobalResponse {
        private ResponseDistritoDTO distrito;
}
