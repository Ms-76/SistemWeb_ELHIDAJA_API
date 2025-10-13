package com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestTipoDocumentoIdDTO {
    @NotNull(message = "El idTipoDocumento es obligatorio")
    @Min(value = 1, message = "El id del tipo de documento debe ser mayor o igual a 1")
    private Long id;
}
