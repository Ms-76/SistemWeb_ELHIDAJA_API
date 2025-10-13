package com.elhidaja.apiselhidaja.presentation.dto.documentoOperacion.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestDocumentoOperacionIdDTO {
    @NotNull(message = "El id_documento_operacion es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor o igual a 1")
    private Integer id;
}
