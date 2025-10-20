package com.elhidaja.apiselhidaja.presentation.dto.tipoOperacion.Request;

import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "nombre", "abreviatura" })
public class RequestTipoOperacionInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "El nombre de la operación no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre de la operación debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[^\\d]*$", message = "El nombre no puede contener números")
    private String nombre;

    @NotBlank(message = "La abreviatura no puede estar vacía")
    @Size(min = 2, max = 10, message = "La abreviatura debe tener entre 2 y 10 caracteres")
    @Pattern(regexp = "^[^\\d]*$", message = "La abreviatura no puede contener números")
    private String abreviatura;
}
