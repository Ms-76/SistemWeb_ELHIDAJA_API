package com.elhidaja.apiselhidaja.presentation.dto.provincia.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProvinciaDTO {
    private Long id;
    private String departamento;
    private String nombre;
    private Boolean status;
}
