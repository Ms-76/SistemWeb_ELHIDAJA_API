package com.elhidaja.apiselhidaja.presentation.dto.documentoOperacion.Request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "id_tipo_documento", "id_tipo_operacion", "codigo_sunat", "codigo_interno" })

public class RequestDocumentoOperacionInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

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
