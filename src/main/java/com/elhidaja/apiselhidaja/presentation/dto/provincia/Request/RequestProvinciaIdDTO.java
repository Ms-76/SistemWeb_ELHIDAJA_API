package com.elhidaja.apiselhidaja.presentation.dto.provincia.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestProvinciaIdDTO {
    @NotNull(message = "El id de la provincia es obligatorio")
    @Min(value = 1, message = "El id de la provincia debe ser mayor o igual a 1")
    private Long id;
}
