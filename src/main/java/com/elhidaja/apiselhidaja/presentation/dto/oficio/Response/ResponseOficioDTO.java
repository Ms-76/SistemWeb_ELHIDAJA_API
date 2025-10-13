package com.elhidaja.apiselhidaja.presentation.dto.oficio.Response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOficioDTO {
    private Long id;
    private String nombre;
    private Boolean status;
}
