package com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectId;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

/**
 * @class RequestCategoriaFilterDTO
 * @brief DTO utilizado para filtrar o buscar una categoría por su ID.
 *
 * Este DTO hereda de {@link RequestObjectId} y se utiliza principalmente
 * en consultas que requieren identificar una categoría específica mediante su ID.
 *
 * Campos heredados:
 * - id: ID de la categoría a buscar.
 */
@Data
@JsonPropertyOrder({ "id" })
public class RequestCategoriaFilterDTO extends RequestObjectId {

}
