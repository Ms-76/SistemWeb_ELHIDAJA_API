package com.elhidaja.apiselhidaja.presentation.dto.area.Response;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "areas", "exito", "mensaje", "codigo" })
public class ResponseAreaAllDTO extends GlobalResponse {
    private List<ResponseAreaDTO> areas;
}
