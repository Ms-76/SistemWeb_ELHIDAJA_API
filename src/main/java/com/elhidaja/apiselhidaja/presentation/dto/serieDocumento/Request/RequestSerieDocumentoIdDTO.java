package com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestSerieDocumentoIdDTO {
        @NotNull(message = "El idSerieDocumento es obligatorio")
    @Min(value = 1, message = "El idSerieDocumento debe ser mayor o igual a 1")
    private Long id;
}
