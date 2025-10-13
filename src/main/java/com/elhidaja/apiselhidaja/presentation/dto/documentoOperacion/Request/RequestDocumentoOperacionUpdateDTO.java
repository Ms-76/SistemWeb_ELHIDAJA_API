package com.elhidaja.apiselhidaja.presentation.dto.documentoOperacion.Request;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDocumentoOperacionUpdateDTO {
    @NotNull(message = "El id_documento_operacion es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor o igual a 1")
    private Integer id;

    @NotNull(message = "El id_tipo_documento es obligatorio")
     @Min(value = 1, message = "El id_tipo_documento debe ser mayor a 0")
    private Integer id_tipo_documento;

    @NotNull(message = "El id_tipo_operacion es obligatorio")
     @Min(value = 1, message = "El id_tipo_operacion debe ser mayor a 0")
    private Integer id_tipo_operacion;

    @NotNull(message = "El código SUNAT es obligatorio")
     @Min(value = 1, message = "El codigo_sunat debe ser mayor a 0")
    private Integer codigo_sunat;

    @NotNull(message = "El código interno es obligatorio")
     @Min(value = 1, message = "El codigo_interno debe ser mayor a 0")
    private Integer codigo_interno;
}
