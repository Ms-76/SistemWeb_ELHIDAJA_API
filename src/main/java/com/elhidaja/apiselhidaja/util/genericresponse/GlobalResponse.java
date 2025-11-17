package com.elhidaja.apiselhidaja.util.genericresponse;

import lombok.Getter;
import lombok.Setter;
/**
 * @class GlobalResponse
 * @brief Clase genérica para estandarizar las respuestas de la API.
 *
 * Esta clase se utiliza como base para todas las respuestas que devuelven
 * un código de estado, un mensaje y un indicador de éxito.
 */
@Getter
@Setter
public class GlobalResponse {
    /**
     * @brief Código de respuesta (por ejemplo, "200", "404", "500").
     */
    private String codigo;

    /**
     * @brief Indica si la operación fue exitosa (true) o no (false).
     */
    private boolean exito;

    /**
     * @brief Mensaje descriptivo sobre el resultado de la operación.
     */
    private String mensaje;
}
