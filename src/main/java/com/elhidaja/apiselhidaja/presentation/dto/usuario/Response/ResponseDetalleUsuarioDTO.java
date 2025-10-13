package com.elhidaja.apiselhidaja.presentation.dto.usuario.Response;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "usuario", "exito", "mensaje",  "codigo" })
public class ResponseDetalleUsuarioDTO extends GlobalResponse{
    private ResponseUsuarioDTO usuario;
}
