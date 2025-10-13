package com.elhidaja.apiselhidaja.presentation.dto.area.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAreaDTO {
    private Long id;
    private String nombre;
    private Boolean status;
}
