package com.elhidaja.apiselhidaja.presentation.dto.oficio.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "nombre" })
public class RequestOficioInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "El nombre del oficio no puede estar vacío")
    @LengthSQL(tabla = "oficio", columna = "nombre")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del oficio debe contener solo letras y espacios")
    private String nombre;
}
