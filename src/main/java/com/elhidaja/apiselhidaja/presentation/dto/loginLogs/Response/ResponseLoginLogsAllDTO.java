package com.elhidaja.apiselhidaja.presentation.dto.loginLogs.Response;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "logs", "exito", "mensaje", "codigo" })
public class ResponseLoginLogsAllDTO extends GlobalResponse{
    private List<ResponseLoginLogDTO> logs;

}
