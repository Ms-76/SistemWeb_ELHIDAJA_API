package com.elhidaja.apiselhidaja.presentation.dto.chofer.Response;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "choferes", "exito", "mensaje", "codigo" })
public class ResponseChoferAllDTO extends GlobalResponse {
    private List<ResponseChoferDTO> choferes;
}


