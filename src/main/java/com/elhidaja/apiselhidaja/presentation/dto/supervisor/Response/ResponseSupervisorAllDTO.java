package com.elhidaja.apiselhidaja.presentation.dto.supervisor.Response;
import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "supervisores", "exito", "mensaje", "codigo" })
public class ResponseSupervisorAllDTO extends GlobalResponse {
        private List<ResponseSupervisorDTO> supervisores;
}
