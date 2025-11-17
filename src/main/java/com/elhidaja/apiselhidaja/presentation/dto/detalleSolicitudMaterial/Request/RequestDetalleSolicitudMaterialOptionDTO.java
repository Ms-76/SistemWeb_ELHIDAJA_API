package com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestDetalleSolicitudMaterialOptionDTO {
    
    @NotNull(message = "El id del proyecto es obligatorio")
    @Min(value = 0, message = "El id del proyecto debe ser mayor o igual o mayor a 0")
    private Long idProyecto;

    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El id del producto es obligatorio")
    @Min(value = 0, message = "El id del producto debe ser mayor o igual a 0")
    private Long idProducto;
}
