package com.elhidaja.apiselhidaja.presentation.dto.serie.Response;
import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonPropertyOrder({ "series", "exito", "mensaje",  "codigo" })
public class ResponseSerieAllDTO extends GlobalResponse{
        List<ResponseSerieDTO> series;
}
