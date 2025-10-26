package com.elhidaja.apiselhidaja.presentation.dto.rol.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "id", "nombre" })
public class RequestRolUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El idRol es obligatorio")
    @Min(value = 1, message = "El id del rol debe ser mayor o igual a 1")
    private Long id;

    @NotBlank(message = "El nombre del rol no puede estar vacío")
    @LengthSQL(tabla = "rol", columna = "nombre")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del rol debe contener solo letras y espacios")
    private String nombre;
}
