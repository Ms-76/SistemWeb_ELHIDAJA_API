package com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import lombok.*;
import jakarta.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUbicacionOptionDTO {
       @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;
}
