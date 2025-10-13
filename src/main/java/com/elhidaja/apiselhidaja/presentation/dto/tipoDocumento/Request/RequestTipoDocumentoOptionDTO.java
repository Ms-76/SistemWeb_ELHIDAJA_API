package com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestTipoDocumentoOptionDTO {
       @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;
}
