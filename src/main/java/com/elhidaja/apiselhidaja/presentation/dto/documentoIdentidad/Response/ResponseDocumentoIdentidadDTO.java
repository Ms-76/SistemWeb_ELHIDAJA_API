package com.elhidaja.apiselhidaja.presentation.dto.documentoIdentidad.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDocumentoIdentidadDTO {
    
     private Long id; 

    private String nombre;

    private String descripcion;

    private Integer longitud;

    private String tipoDocumento;

    private Boolean status;
}
