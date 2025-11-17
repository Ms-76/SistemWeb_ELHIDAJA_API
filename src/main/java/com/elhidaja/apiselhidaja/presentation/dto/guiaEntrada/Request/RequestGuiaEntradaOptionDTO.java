package com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Request;

import java.time.LocalDate;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@JsonPropertyOrder({ "estado", "idAlmacen" })
public class RequestGuiaEntradaOptionDTO {
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El id del almacen es obligatorio")
    @Min(value = 0, message = "El id del almacen debe ser mayor o igual a 0")
    private Long idAlmacen;

    @NotNull(message = "El id del proveedor es obligatorio")
    @Min(value = 0, message = "El id del proveedor debe ser mayor o igual a 0")
    private Long idProveedor;

    @NotNull(message = "El id de la serie del documento es obligatorio")
    @Min(value = 0, message = "El id de la serie del documento debe ser mayor o igual a 0")
    private Long idSerieDocumento;

    @NotNull(message = "El id del usuario es obligatorio")
    @Min(value = 0, message = "El id del usuario debe ser mayor o igual a 0")
    private Long idUsuario;

    @NotNull(message = "El la fecha inicio es obligatorio")
    private LocalDate fechaInicio;

    @NotNull(message = "El la fecha fin es obligatorio")
    private LocalDate fechaFin;
}
