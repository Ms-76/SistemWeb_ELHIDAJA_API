package com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Response;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "guiaEntrada", "exito", "mensaje", "codigo" })
public class ResponseDetalleGuiaEntradaDTO extends GlobalResponse {
    private ResponseGuiaEntradaDTO guiaEntrada;
}
