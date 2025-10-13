package com.elhidaja.apiselhidaja.presentation.dto.almacen.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import lombok.*;
import jakarta.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAlmacenOptionDTO {
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado; // cambiar a estado
}
