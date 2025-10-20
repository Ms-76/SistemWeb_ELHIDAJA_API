package com.elhidaja.apiselhidaja.presentation.dto.arquitecto.Request;

import lombok.Data;
import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import jakarta.validation.constraints.NotNull;

@Data
public class RequestArquitectoOptionDTO {
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;
}
