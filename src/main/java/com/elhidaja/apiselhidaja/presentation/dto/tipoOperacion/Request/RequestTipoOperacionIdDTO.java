package com.elhidaja.apiselhidaja.presentation.dto.tipoOperacion.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestTipoOperacionIdDTO {
    @NotNull(message = "El idTipoOperacion es obligatorio")
    @Min(value = 1, message = "El id de la operación debe ser mayor o igual a 1")
    private Long id;
}
