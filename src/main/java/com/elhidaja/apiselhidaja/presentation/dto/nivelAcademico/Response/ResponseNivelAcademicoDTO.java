package com.elhidaja.apiselhidaja.presentation.dto.nivelAcademico.Response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseNivelAcademicoDTO {
    private Long id;
    private String nombre;
    private Boolean status;
}
