package com.elhidaja.apiselhidaja.presentation.dto.inventario.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestInventarioOptionDTO {
       @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;
}
