package com.elhidaja.apiselhidaja.presentation.dto.tipoOperacion.Response;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTipoOperacionDTO {
    private Long id;
    private String nombre;
    private String abreviatura;
    private Boolean status;
}
