package com.elhidaja.apiselhidaja.presentation.dto.serie.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class RequestSerieIdDTO {
    @NotNull(message = "El idSerie es obligatorio")
    @Min(value = 1, message = "El id de la serie debe ser mayor o igual a 1")
    private Long id;
}
