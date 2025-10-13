package com.elhidaja.apiselhidaja.presentation.dto.loginLogs.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestLoginLogIdDTO {

    @NotNull(message = "El id del usuario es obligatorio")
    @Min(value = 1, message = "El id del usuario debe ser mayor o igual a 1")
    private Long id;
}
