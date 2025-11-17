package com.elhidaja.apiselhidaja.presentation.dto.documentoOperacion.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestDocumentoOperacionOptionDTO {
    
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El id_tipo_documento es obligatorio")
    @Min(value = 0, message = "El id_tipo_documento debe ser mayor a 0")
    private Integer id_tipo_documento;

    @NotNull(message = "El id_tipo_operacion es obligatorio")
    @Min(value = 0, message = "El id_tipo_operacion debe ser mayor a 0")
    private Integer id_tipo_operacion;

    @NotNull(message = "El código interno es obligatorio")
    @Min(value = 0, message = "El codigo_interno debe ser mayor a 0")
    private Integer codigo_interno;
}
