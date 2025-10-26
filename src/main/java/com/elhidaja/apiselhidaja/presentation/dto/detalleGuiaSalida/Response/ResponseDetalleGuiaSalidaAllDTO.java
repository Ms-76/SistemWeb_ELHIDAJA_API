package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaSalida.Response;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "detalleGuiasSalida", "exito", "mensaje",  "codigo" })
public class ResponseDetalleGuiaSalidaAllDTO  extends GlobalResponse {
    List<ResponseDetalleGuiaSalidaDTOInner> detalleGuiasSalida;
}
