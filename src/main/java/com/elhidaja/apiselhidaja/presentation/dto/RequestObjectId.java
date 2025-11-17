package com.elhidaja.apiselhidaja.presentation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @class RequestObjectId
 * @brief Clase base para DTOs que solo requieren un ID.
 *
 *        Esta clase se utiliza como base para varios DTOs de solicitudes
 *        que necesitan identificar un objeto por su ID.
 */
@Data
public class RequestObjectId {
    /**
     * @brief ID del objeto.
     *
     *        Se utiliza para identificar de manera única el recurso
     *        al que se aplicará la operación (consulta, actualización, etc.).
     *        Debe ser mayor o igual a 1.
     */
    @NotNull(message = "El id es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor o igual a 1")
    private Long id;
}
