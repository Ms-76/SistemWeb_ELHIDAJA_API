package com.elhidaja.apiselhidaja.presentation.dto.serie.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "serie", "descripcion" })
public class RequestSerieInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "La serie no puede estar vacía")
    @LengthSQL(tabla = "serie", columna = "serie")
    private String serie;

    @NotBlank(message = "La descripción no puede estar vacía")
    @LengthSQL(tabla = "serie", columna = "descripcion")
    private String descripcion;
}
