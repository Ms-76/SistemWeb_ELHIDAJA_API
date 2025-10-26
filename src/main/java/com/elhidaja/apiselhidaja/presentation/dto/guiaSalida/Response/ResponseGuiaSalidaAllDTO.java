package com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Response;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "guiasSalida", "exito", "mensaje", "codigo" })
public class ResponseGuiaSalidaAllDTO extends GlobalResponse {
    List<ResponseGuiaSalidaDTO> guiasSalida;
}
