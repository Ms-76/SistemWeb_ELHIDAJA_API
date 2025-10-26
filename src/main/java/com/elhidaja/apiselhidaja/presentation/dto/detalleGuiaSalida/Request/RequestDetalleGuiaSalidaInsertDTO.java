package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaSalida.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;

import jakarta.validation.constraints.*;
import lombok.Data;
@Data
public class RequestDetalleGuiaSalidaInsertDTO {

    @NotNull(message = "El ID del producto es obligatorio")
    @Min(value = 1, message = "El ID del producto debe ser mayor que cero")
    private Long id;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor que cero")
    private Integer cantidad;

    @NotBlank(message = "La observación es obligatoria")
    @LengthSQL(tabla = "detalle_solicitud_material", columna = "observacion")
    private String observacion;

    @NotNull(message = "El ID de la unidad medida es obligatorio")
    @Min(value = 1, message = "El ID de la unidad medida debe ser mayor que cero")
    private Long idUnidadMedida;

}
