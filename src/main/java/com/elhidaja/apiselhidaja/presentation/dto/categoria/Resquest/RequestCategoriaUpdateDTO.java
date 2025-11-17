package com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @class RequestCategoriaUpdateDTO
 * @brief DTO para actualizar una categoría existente.
 *
 * Este DTO se utiliza en las operaciones de actualización de categorías,
 * proporcionando el ID del usuario que realiza la acción, el ID de la categoría
 * a actualizar y el nuevo nombre de la categoría.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin","id", "nombre" })
public class RequestCategoriaUpdateDTO {
    
    /** 
     * @brief ID del usuario que realiza la actualización.
     * @note Debe ser mayor o igual a 1.
     */
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;
    /** 
     * @brief ID de la categoría que se desea actualizar.
     * @note Debe ser mayor o igual a 1.
     */
    @NotNull(message = "El idCategoria es obligatorio")
    @Min(value = 1, message = "El id de la categoría debe ser mayor o igual a 1")
    private Long id;
    /** 
     * @brief Nuevo nombre de la categoría.
     * @note Solo permite letras y espacios, no puede estar vacío y se valida contra la longitud de la columna SQL.
     */
    @NotBlank(message = "El nombre de la categoria no puede estar vacío")
    @LengthSQL(tabla = "categoria", columna = "nombre")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "el nombre de la categoria solo puede letras y espacios")
    private String nombre;
}
