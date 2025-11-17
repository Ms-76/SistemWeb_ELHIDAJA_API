package com.elhidaja.apiselhidaja.presentation.dto.distrito.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestDistritoOptionDTO {
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El id del departamento es obligatorio")
    @Min(value = 0, message = "El id del departamento debe ser mayor o igual a 0")
    private Long idDepartamento;

    @NotNull(message = "El idProvincia es obligatorio")
    @Min(value = 0, message = "El idProvincia debe ser mayor o igual a 0")
    private Long idProvincia;
}
