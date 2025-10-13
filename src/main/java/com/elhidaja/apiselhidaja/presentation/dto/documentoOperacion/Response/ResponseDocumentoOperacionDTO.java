package com.elhidaja.apiselhidaja.presentation.dto.documentoOperacion.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDocumentoOperacionDTO {
    private Integer id;
    private String nombre_tipo_documento;
    private String nombre_tipo_operacion;
    private Integer codigo_sunat;
    private Integer codigo_interno;
    private Boolean status;
}
