package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonPropertyOrder({ "estado", "idAlmacen" })
public class RequestDetalleGuiaEntradaOptionDTO {
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El id del almacen es obligatorio")
    @Min(value = 0, message = "El id del almacen debe ser mayor o igual a 0")
    private Long idAlmacen;

    @NotNull(message = "El ID del producto es obligatorio")
    @Min(value = 0, message = "El ID del producto debe ser mayor que cero")
    private Long idProducto;

    @NotNull(message = "El id de la unidad_medida es obligatorio")
    @Min(value = 0, message = "El id de la unidad_medida debe ser mayor o igual a 0")
    private Integer idUnidadMedida;

    @NotNull(message = "El id de la guia entrada es obligatorio")
    @Min(value = 0, message = "El id de la guia entrada debe ser mayor o igual a 0")
    private Integer idGuiaEntrada;
}
