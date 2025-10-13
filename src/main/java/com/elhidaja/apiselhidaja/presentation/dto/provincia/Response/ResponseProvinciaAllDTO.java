package com.elhidaja.apiselhidaja.presentation.dto.provincia.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@Getter
@Setter
@JsonPropertyOrder({ "provincias", "exito", "mensaje",  "codigo" })
public class ResponseProvinciaAllDTO  extends GlobalResponse{
        private List<ResponseProvinciaDTO> provincias;

}
