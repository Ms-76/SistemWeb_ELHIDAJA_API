package com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin","id", "nombre" })
public class RequestCategoriaUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El idCategoria es obligatorio")
    @Min(value = 1, message = "El id de la categoría debe ser mayor o igual a 1")
    private Long id;

    @NotBlank(message = "El nombre de la categoria no puede estar vacío")
    @LengthSQL(tabla = "categoria", columna = "nombre")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "el nombre de la categoria solo puede letras y espacios")
    private String nombre;
}
