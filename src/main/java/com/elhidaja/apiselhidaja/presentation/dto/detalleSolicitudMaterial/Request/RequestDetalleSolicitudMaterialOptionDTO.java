package com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonPropertyOrder({ "idProyecto", "estado" })
public class RequestDetalleSolicitudMaterialOptionDTO {
    @NotNull(message = "El id del proyecto es obligatorio")
    @Min(value = 1, message = "El id del proyecto debe ser mayor o igual a 1")
    private Long idProyecto;

    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;
}
