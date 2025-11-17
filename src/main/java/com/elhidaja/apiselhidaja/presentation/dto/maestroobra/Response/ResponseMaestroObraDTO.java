package com.elhidaja.apiselhidaja.presentation.dto.maestroobra.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMaestroObraDTO {
    private Long id;
    private String documento;
    private String numeroDocumento;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;
    private String departamento;
    private String provincia;
    private String distrito;
    private Boolean status;
    private Integer experienciaAnios;
}
