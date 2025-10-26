package com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Request;

import lombok.Data;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.EstadoValido;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;

@Data
@JsonPropertyOrder({ "idLogin", "id", "stockFisico", "diferencia", "observacion", "fechaFinInventario", "nuevo",
        "editadoManual", "estado" })
public class RequestDetalleInventarioUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")

    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El id del detalle inventario es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor o igual a 1")
    private Long id;

    @NotNull(message = "El stock fisico es obligatorio")
    @Min(value = 0, message = "La cantidad debe ser mayor o igual a 0")
    private Integer stockFisico;

    @NotBlank(message = "La observacion no puede estar vacía")
    @Size(max = 255, message = "La observación debe tener máximo 255 caracteres")
    private String observacion;

    @NotNull(message = "El campo eeditado manual es obligatorio")
    private Boolean editadoManual;

    @NotNull(message = "El el estado es obligatorio")
    @EstadoValido
    private Integer estado;
}
