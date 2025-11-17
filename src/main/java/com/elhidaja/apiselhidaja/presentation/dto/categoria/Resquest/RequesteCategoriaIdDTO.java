package com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
/**
 * @class RequesteCategoriaIdDTO
 * @brief DTO que representa la información necesaria para operaciones sobre una categoría específica.
 *
 * Hereda de {@link RequestObjectActionId}, incluyendo:
 * - idLogin: ID del usuario que realiza la acción.
 * - id: ID de la categoría sobre la que se realizará la acción.
 *
 * Se utiliza en operaciones como activar, desactivar o eliminar categorías.
 */
@Data
@JsonPropertyOrder({ "idLogin", "id" })
public class RequesteCategoriaIdDTO extends RequestObjectActionId {

}
