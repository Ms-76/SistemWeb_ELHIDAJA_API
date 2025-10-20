package com.elhidaja.apiselhidaja.presentation.dto.supervisor.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"idLogin","id"})
public class RequestSupervisorIdDTO extends RequestObjectActionId {
}
