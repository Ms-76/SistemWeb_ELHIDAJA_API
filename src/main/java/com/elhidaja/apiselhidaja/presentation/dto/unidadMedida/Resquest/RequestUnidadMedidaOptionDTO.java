package com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestUnidadMedidaOptionDTO {

    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;
}
