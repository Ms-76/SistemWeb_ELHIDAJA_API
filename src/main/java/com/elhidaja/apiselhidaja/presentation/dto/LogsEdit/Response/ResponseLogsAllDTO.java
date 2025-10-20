package com.elhidaja.apiselhidaja.presentation.dto.LogsEdit.Response;

import java.util.List;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "logs", "exito", "mensaje", "codigo" })
public class ResponseLogsAllDTO extends GlobalResponse{
    private List<ResponseLogDTO> logs;
}
