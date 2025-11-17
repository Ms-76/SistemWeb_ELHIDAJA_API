package com.elhidaja.apiselhidaja.presentation.dto.ingeniero.Request;

import lombok.Data;
import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class RequestIngenieroOptionDTO {
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;
    @NotNull(message = "El idProvincia es obligatorio")
    @Min(value = 0, message = "El idProvincia debe ser mayor o igual a 0")
    private Long idProvincia;

    @NotNull(message = "El id del departamento es obligatorio")
    @Min(value = 0, message = "El id del departamento debe ser mayor o igual a 0")
    private Long idDepartamento;

    @NotNull(message = "El ID del distrito es obligatorio")
    @Min(value = 0, message = "ID de distrito debe ser mayor o igual a 0")
    private Integer idDistrito;

    @NotNull(message = "El ID del documento de identidad es obligatorio")
    @Min(value = 0, message = "ID de documento de identidad debe ser mayor o igual a 0")
    private Integer idDocumentoIdentidad;
}
