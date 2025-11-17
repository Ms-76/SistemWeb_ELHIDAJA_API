package com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Response;

import java.time.LocalDate;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGuiaSalidaDTO {
    private Long id;
    private String tipoOperacion;
    private String tipoDocumento;
    private String trabajador;
    private String tipoDestino;
    private String destino;
    private LocalDate fechaSalida;
    private String descripcion;
    private Boolean status;
}
