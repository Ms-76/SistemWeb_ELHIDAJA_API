package com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;

import jakarta.validation.constraints.*;
import lombok.Data;
@Data
public class RequestDetalleSolicitudMaterialInsert {
    @NotNull(message = "El id del producto es obligatorio")
    @Min(value = 1, message = "El id del producto debe ser mayor o igual a 1")
    private Integer idProducto;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor que cero")
    private Integer cantidad;

    @NotBlank(message = "La observación es obligatoria")
    @LengthSQL(tabla = "detalle_solicitud_material", columna = "observacion")
    private String observacion;
}
