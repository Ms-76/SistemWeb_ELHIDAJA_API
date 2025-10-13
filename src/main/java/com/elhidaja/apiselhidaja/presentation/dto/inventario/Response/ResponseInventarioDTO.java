package com.elhidaja.apiselhidaja.presentation.dto.inventario.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseInventarioDTO {
    private Long id;
    private LocalDate fecha;
    private String supervisor;
    private String descripcion;
    private Boolean status;
}
