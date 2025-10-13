package com.elhidaja.apiselhidaja.presentation.dto.loginLogs.Response;
import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "userLogs", "exito", "mensaje", "codigo" })
public class ResponseLoginLogByIdDTO extends GlobalResponse {
        private List<ResponseLoginLogDTO> userLogs;
}
