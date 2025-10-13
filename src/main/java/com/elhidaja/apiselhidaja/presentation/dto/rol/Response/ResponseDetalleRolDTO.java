package com.elhidaja.apiselhidaja.presentation.dto.rol.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "rol", "exito", "mensaje", "codigo" })
public class ResponseDetalleRolDTO extends GlobalResponse {
    private ResponseRolDTO rol;
}
