package com.elhidaja.apiselhidaja.presentation.dto.serie.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSerieDTO {
    private Long id;
    private String serie;
    private String descripcion;
    private Boolean status;
}
