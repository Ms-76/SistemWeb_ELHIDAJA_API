package com.elhidaja.apiselhidaja.presentation.dto.rol.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRolDTO {

    private Long id;
    private String nombre;
    private Boolean status;
}
