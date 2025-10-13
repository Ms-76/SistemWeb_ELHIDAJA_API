package com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGuiaEntradaDTO {
    private Long id;
    private String proveedor;
    private String trabajador;
    private String descripcion;
    private LocalDate fechaEntrada;
}
