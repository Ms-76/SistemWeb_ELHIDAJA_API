package com.elhidaja.apiselhidaja.presentation.dto.puesto.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class RequestPuestoOptionDTO {
       @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;
}
