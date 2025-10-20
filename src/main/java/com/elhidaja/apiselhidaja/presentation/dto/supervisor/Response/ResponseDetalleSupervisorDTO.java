package com.elhidaja.apiselhidaja.presentation.dto.supervisor.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "supervisor", "exito", "mensaje", "codigo" })
public class ResponseDetalleSupervisorDTO extends GlobalResponse {
    private ResponseSupervisorDTO supervisor;
}
