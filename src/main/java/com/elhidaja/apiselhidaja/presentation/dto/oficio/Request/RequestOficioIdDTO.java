package com.elhidaja.apiselhidaja.presentation.dto.oficio.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class RequestOficioIdDTO {
    @NotNull(message = "El idOficio es obligatorio")
    @Min(value = 1, message = "El id del oficio debe ser mayor o igual a 1")
    private Long id;
}
