package com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
/**
 * @class ResquestCategoriaOptionDTO
 * @brief DTO para filtrar y consultar categorías según opciones específicas.
 *
 * Se utiliza principalmente para obtener todas las categorías según un estado
 * determinado, como activas o inactivas.
 */
@Data
public class ResquestCategoriaOptionDTO {
    /**
     * @brief Estado de la categoría a consultar.
     *
     * Obligatorio. Debe cumplir con las restricciones definidas en @ValidOption.
     * Por ejemplo, 1 para activo, 0 para inactivo.
     */
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;
}
