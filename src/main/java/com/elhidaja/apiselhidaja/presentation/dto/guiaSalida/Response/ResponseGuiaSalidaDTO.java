package com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Response;
import java.time.LocalDate;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGuiaSalidaDTO {
    private Long id;
    private String proveedor;
    private String trabajador;
    private String descripcion;
    private LocalDate fechaSalida;
}
