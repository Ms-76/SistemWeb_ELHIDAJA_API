package com.elhidaja.apiselhidaja.presentation.dto.puesto.Response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePuestoDTO {
    private Long id;
    private String nombre;
    private Boolean status;
}
