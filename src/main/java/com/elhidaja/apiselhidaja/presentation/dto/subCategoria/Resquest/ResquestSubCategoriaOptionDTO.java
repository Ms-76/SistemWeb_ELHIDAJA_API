package com.elhidaja.apiselhidaja.presentation.dto.subCategoria.Resquest;
import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResquestSubCategoriaOptionDTO {

    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;
}
