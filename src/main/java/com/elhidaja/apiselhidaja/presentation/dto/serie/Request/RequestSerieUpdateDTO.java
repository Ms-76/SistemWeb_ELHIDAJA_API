package com.elhidaja.apiselhidaja.presentation.dto.serie.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "id", "serie", "descripcion" })

public class RequestSerieUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El idSerie es obligatorio")
    @Min(value = 1, message = "El id de la serie debe ser mayor o igual a 1")
    private Long id;

    @NotBlank(message = "La serie no puede estar vacía")
    @LengthSQL(tabla = "serie", columna = "serie")
    private String serie;

    @NotBlank(message = "La descripción no puede estar vacía")
    @LengthSQL(tabla = "serie", columna = "descripcion")
    private String descripcion;
}
