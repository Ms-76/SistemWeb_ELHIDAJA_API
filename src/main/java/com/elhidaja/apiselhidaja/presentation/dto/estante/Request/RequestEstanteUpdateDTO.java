package com.elhidaja.apiselhidaja.presentation.dto.estante.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "idLogin","id", "codigo", "descripcion" })
public class RequestEstanteUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El id_estante es obligatorio")
    @Min(value = 1, message = "El id del estante debe ser mayor o igual a 1")
    private Long id;

    @NotBlank(message = "El código no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9\\-\\s]+$", message = "El código del estante solo puede contener letras, números, espacios y guiones")
    @LengthSQL(tabla = "estante", columna = "codigo")
    private String codigo;

    @NotBlank(message = "La descripción no puede estar vacía")
    @LengthSQL(tabla = "estante", columna = "descripcion")
    private String descripcion;
}
