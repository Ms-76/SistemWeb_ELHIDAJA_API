package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaEntrada.Request;


import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "idDetalleGuiaEntrada", "observacion" })
public class RequestActualizarObservacionDetalleGuiaEntradaDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El idDetalleGuiaEntrada es obligatorio")
    @Min(value = 1, message = "El idDetalleGuiaEntrada debe ser mayor o igual a 1")
    private Integer idDetalleGuiaEntrada;

    @LengthSQL(tabla = "detalle_guia_entrada", columna = "observacion")
    private String observacion;
}
