package com.elhidaja.apiselhidaja.presentation.dto.LogsEdit.Response;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "log", "exito", "mensaje", "codigo" })
public class ResponseDetalleLogDTO extends GlobalResponse {
    private ResponseLogDTO log;
}
