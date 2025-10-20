package com.elhidaja.apiselhidaja.presentation.dto.LogsEdit.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestLogIdDTO {
    @NotNull(message = "El id es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor o igual a 1")
    private Long id;
}
