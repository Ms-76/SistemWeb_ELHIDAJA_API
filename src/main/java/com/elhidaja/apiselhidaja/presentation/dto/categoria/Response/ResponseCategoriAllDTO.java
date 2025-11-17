package com.elhidaja.apiselhidaja.presentation.dto.categoria.Response;

import java.util.List;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

/**
 * @class ResponseCategoriAllDTO
 * @brief DTO para devolver una lista de categorías.
 *
 *        Hereda de {@link GlobalResponse} e incluye:
 *        - {@link #categorias}: lista de objetos {@link ResponseCategoriaDTO}
 *        con las categorías encontradas.
 *        - codigo, exito, mensaje (heredados de GlobalResponse)
 *
 *        Se utiliza principalmente en respuestas de consultas que devuelven
 *        todas las categorías o un conjunto filtrado de categorías.
 */
@Getter
@Setter
@JsonPropertyOrder({ "categorias", "exito", "mensaje", "codigo" })
public class ResponseCategoriAllDTO extends GlobalResponse {
    /**
     * @brief Lista de categorías encontradas.
     */
    List<ResponseCategoriaDTO> categorias;
}
