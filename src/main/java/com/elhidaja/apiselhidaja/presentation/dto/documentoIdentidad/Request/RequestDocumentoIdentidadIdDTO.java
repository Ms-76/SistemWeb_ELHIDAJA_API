package com.elhidaja.apiselhidaja.presentation.dto.documentoIdentidad.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class RequestDocumentoIdentidadIdDTO {
     @NotNull(message = "El id es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor o igual a 1")
    private Long id;
}
