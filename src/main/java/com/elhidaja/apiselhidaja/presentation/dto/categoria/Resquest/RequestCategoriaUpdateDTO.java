package com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest;

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
    @Size(min = 3, max = 100, message = "El nombre de la categoria debe tener entre 3 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre de la categoria contener letras y espacios")
    private String nombre;
}
