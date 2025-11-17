package com.elhidaja.apiselhidaja.presentation.dto.categoria.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @class ResponseCategoriaDTO
 * @brief DTO que representa la información básica de una categoría.
 *
 * Este DTO se utiliza en las respuestas de las APIs que retornan información
 * de categorías, incluyendo su ID, nombre y estado.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCategoriaDTO  {

    /**
     * @brief ID único de la categoría.
     */
    private Long id;

    /**
     * @brief Nombre de la categoría.
     */
    private String nombre;

    /**
     * @brief Estado de la categoría (true = activa, false = inactiva).
     */
    private Boolean status;
}
