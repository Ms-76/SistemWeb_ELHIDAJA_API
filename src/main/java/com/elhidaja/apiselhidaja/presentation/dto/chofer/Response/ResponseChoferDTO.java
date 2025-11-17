package com.elhidaja.apiselhidaja.presentation.dto.chofer.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseChoferDTO {
    private Long id;
    private String documento;
    private String numeroDocumento;
    private String nombre;
    private String email;
    private String telefono;
    private String licenciaConducir;
    private String categoriaLicencia;
    private LocalDate fechaEmisionLicencia;
    private LocalDate fechaVencimientoLicencia;
    private Integer experienciaAnios;
    private String direccion;
    private String departamento;
    private String provincia;
    private String distrito;
    private Boolean status;
}