package com.elhidaja.apiselhidaja.presentation.dto.LogsEdit.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseLogDTO {
    private Long id;
    private Long idUsuario;
    private String tabla;
    private String tipoAccion;
    private String camposAfectados;
    private String fechaAccion;
}
