package com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Response;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "guiaSalida", "exito", "mensaje", "codigo" })
public class ResponseDetalleGuiaSalidaDTO extends GlobalResponse {
    private ResponseGuiaSalidaDTO guiaSalida;
}
