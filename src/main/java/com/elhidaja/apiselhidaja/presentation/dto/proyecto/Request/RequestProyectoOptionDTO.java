package com.elhidaja.apiselhidaja.presentation.dto.proyecto.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestProyectoOptionDTO {
    
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;
}
