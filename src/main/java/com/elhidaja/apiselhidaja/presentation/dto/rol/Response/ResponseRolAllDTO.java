package com.elhidaja.apiselhidaja.presentation.dto.rol.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "roles", "exito", "mensaje", "codigo" })
public class ResponseRolAllDTO extends GlobalResponse {
    private List<ResponseRolDTO> roles;
}
