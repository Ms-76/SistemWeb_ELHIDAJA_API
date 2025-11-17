package com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @class RequestCategoriaInsertDTO
 * @brief DTO para la inserción de una nueva categoría.
 * 
 * Contiene la información necesaria para crear una nueva categoría en el sistema.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "nombre" })
public class RequestCategoriaInsertDTO {
    /**
     * @brief ID del usuario que realiza la operación.
     * @note Debe ser mayor o igual a 1 y no puede ser nulo.
     */
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;
    /**
     * @brief Nombre de la categoría a insertar.
     * @note No puede estar vacío, solo puede contener letras y espacios.
     *       Valida además la longitud según la columna 'nombre' de la tabla 'categoria'.
     */
    @NotBlank(message = "El nombre de la categoria no puede estar vacío")
    @LengthSQL(tabla = "categoria", columna = "nombre")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "el nombre de la categoria solo puede letras y espacios")
    private String nombre;

}
