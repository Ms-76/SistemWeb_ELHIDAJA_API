package com.elhidaja.apiselhidaja.presentation.dto.usuario.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "usuarios", "exito", "mensaje", "codigo" })
public class ResponseUsuarioAllDTO extends GlobalResponse {
    private List<ResponseUsuarioDTO> usuarios;
}
