package com.elhidaja.apiselhidaja.presentation.dto.estante.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "estado", "idAlmacen"})
public class RequestEstanteOptionDTO {
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El id del almacen es obligatorio")
    @Min(value = 0, message = "El id del almacen debe ser mayor o igual a 0")
    private Long idAlmacen;
}
