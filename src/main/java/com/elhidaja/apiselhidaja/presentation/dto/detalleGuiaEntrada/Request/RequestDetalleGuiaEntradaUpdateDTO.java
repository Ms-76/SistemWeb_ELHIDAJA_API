package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonPropertyOrder({ "idLogin", "idDetalleGuiaEntrada", "idGuiaEntrada", "fechaVencimientoProducto",
        "idProducto", "cantidad", "idUnidadMedida", "observacion" })
public class RequestDetalleGuiaEntradaUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El idDetalleGuiaEntrada es obligatorio")
    @Min(value = 1, message = "El idDetalleGuiaEntrada debe ser mayor o igual a 1")
    private Integer idDetalleGuiaEntrada;

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
