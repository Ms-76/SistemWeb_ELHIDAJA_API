package com.elhidaja.apiselhidaja.presentation.dto.oficio.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import lombok.*;

@Getter
@Setter
@JsonPropertyOrder({ "oficios", "exito", "mensaje", "codigo" })
public class ResponseOficioAllDTO  extends GlobalResponse{
    List<ResponseOficioDTO> oficios;
}
