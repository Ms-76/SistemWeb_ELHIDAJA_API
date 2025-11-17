package com.elhidaja.apiselhidaja.presentation.dto.ubicacion.Request;

import com.elhidaja.apiselhidaja.presentation.dto.RequestObjectActionId;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class RequestUbicacionIdDTO extends RequestObjectActionId {
    @NotNull(message = "El ID del almacen es obligatorio")
    @Min(value = 0, message = "El ID del almacen debe ser mayor que cero")
    private Long idAlmacen;
}
