package com.elhidaja.apiselhidaja.presentation.dto.proyecto.Response;
import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "proyectos", "exito", "mensaje",  "codigo" })
public class ResponseProyectoAllDTO extends GlobalResponse{
        List<ResponseProyectoDTO> proyectos;

}
