package com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSerieDocumentoInsertDTO {
    @NotNull(message = "El id de la serie es obligatorio")
    @Min(value = 1, message = "El id de la serie debe ser mayor o igual a 1")
    private Long idSerie;

    @NotNull(message = "El id del documento operación es obligatorio")
    @Min(value = 1, message = "El id del documento operación debe ser mayor o igual a 1")
    private Long idDocumentoOperacion;

    @Min(value = 0, message = "El último correlativo no puede ser negativo")
    private Long ultimoCorrelativo;
}
