package com.elhidaja.apiselhidaja.presentation.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @class RequestObjectActionId
 * @brief Clase base para DTOs que requieren tanto el ID del usuario como el ID
 *        del objeto.
 *
 *        Esta clase se utiliza cuando una operación requiere identificar
 *        tanto al usuario que realiza la acción como el recurso sobre el cual
 *        se aplicará (por ejemplo, activar o desactivar una categoría).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestObjectActionId {
    /**
     * @brief ID del usuario que realiza la acción.
     *
     *        Obligatorio, debe ser mayor o igual a 1.
     */
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;
    /**
     * @brief ID del objeto sobre el que se aplicará la acción.
     *
     *        Obligatorio, debe ser mayor o igual a 1.
     */
    @NotNull(message = "El id es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor o igual a 1")
    private Long id;

}
