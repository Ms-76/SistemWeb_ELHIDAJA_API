package com.elhidaja.apiselhidaja.presentation.dto.puesto.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "id", "nombre" })
public class RequestPuestoUpdateDTO {
    
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El idPuesto es obligatorio")
    @Min(value = 1, message = "El id del puesto debe ser mayor o igual a 1")
    private Long id;

    @NotBlank(message = "El nombre del puesto no puede estar vacío")
    @LengthSQL(tabla = "puesto", columna = "nombre")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del puesto debe contener letras y espacios")
    private String nombre;
}
