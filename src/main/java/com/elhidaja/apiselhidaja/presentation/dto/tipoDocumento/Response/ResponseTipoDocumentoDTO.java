package com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTipoDocumentoDTO {
    private Long id;
    private String nombre;
    private Boolean status;
}
