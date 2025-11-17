package com.elhidaja.apiselhidaja.presentation.dto.serieDocumento.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestSerieDocumentoOptionDTO {
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;
    
    @NotNull(message = "El id de la serie es obligatorio")
    @Min(value = 0, message = "El id de la serie debe ser mayor o igual a 0")
    private Long idSerie;

    @NotNull(message = "El id del documento operación es obligatorio")
    @Min(value = 0, message = "El id del documento operación debe ser mayor o igual a 0")
    private Long idDocumentoOperacion;
}
