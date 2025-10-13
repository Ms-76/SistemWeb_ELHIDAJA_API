package com.elhidaja.apiselhidaja.presentation.dto.distrito.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDistritoDTO {
    private Long id;
    private String departamento;
    private String provincia;
    private String nombre;
    private Boolean status;
}
