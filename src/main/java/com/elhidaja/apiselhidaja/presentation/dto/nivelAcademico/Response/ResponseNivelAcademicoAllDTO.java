package com.elhidaja.apiselhidaja.presentation.dto.nivelAcademico.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
@JsonPropertyOrder({ "nivelesAcademicos", "exito", "mensaje",  "codigo" })
@Getter
@Setter
public class ResponseNivelAcademicoAllDTO  extends GlobalResponse{
       List<ResponseNivelAcademicoDTO> nivelesAcademicos;
}
