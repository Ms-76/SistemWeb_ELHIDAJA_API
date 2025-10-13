package com.elhidaja.apiselhidaja.presentation.dto.departamento.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDepartamentoDTO {
    private Long id;
    private String nombre;
    private Boolean status;
}
