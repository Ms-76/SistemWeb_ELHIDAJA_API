package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaSalida.Response;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "detalleGuiaSalida", "exito", "mensaje",  "codigo" })
public class ResponseDetalleGuiaSalidaDTO extends GlobalResponse {
    private ResponseDetalleGuiaSalidaDTOInner detalleGuiaSalida;
}
