package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDetalleGuiaEntradaInsertDTO {
    
    @NotNull(message = "El idGuiaEntrada es obligatorio")
    private Integer idGuiaEntrada;

    private LocalDateTime fechaVencimientoProducto;

    @NotNull(message = "El idProducto es obligatorio")
    private Integer idProducto;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor o igual a 1")
    private Integer cantidad;

    @NotNull(message = "El idUnidadMedida es obligatorio")
    private Integer idUnidadMedida;

    @Size(max = 255, message = "La observación debe tener máximo 255 caracteres")
    private String observacion;
}
