package com.elhidaja.apiselhidaja.presentation.dto.distrito.Response;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@JsonPropertyOrder({ "distritos", "exito", "mensaje", "codigo" })
public class ResponseDistritoAllDTO extends GlobalResponse{
     private List<ResponseDistritoDTO> distritos;
}
