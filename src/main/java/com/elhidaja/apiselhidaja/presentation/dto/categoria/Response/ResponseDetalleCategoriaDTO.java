package com.elhidaja.apiselhidaja.presentation.dto.categoria.Response;

import com.elhidaja.apiselhidaja.util.genericresponse.GlobalResponse;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @class ResponseDetalleCategoriaDTO
 * @brief DTO para devolver los detalles de una categoría específica.
 *
 *        Hereda de {@link GlobalResponse} e incluye:
 *        - {@link #categoria}: objeto {@link ResponseCategoriaDTO} con los
 *        detalles de la categoría.
 *        - codigo, exito, mensaje (heredados de GlobalResponse)
 *
 *        Se utiliza principalmente en respuestas de consultas que buscan una
 *        categoría
 *        por su ID.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "categoria", "exito", "mensaje", "codigo" })
public class ResponseDetalleCategoriaDTO extends GlobalResponse {
    /**
     * @brief Datos de la categoría solicitada.
     */
    private ResponseCategoriaDTO categoria;
}
