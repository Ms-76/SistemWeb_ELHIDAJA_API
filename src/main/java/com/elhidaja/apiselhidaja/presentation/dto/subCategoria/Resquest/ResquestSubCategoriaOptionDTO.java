package com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Resquest;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResquestSubCategoriaOptionDTO {

    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;
    
    @NotNull(message = "El id de la categoría es obligatorio")
    @Min(value = 0, message = "El id de la categoría debe ser mayor o igual a 0")
    private Long idCategoria;
}
