package com.elhidaja.apiselhidaja.presentation.dto.serie.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class RequestSerieOptionDTO {

       @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;
}
