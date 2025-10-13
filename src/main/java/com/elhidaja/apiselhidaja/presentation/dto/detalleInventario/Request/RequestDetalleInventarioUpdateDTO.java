package com.elhidaja.apiselhidaja.presentation.dto.detalleInventario.Request;

import lombok.Data;

import java.time.LocalDateTime;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.EstadoValido;

import jakarta.validation.constraints.*;

@Data
public class RequestDetalleInventarioUpdateDTO {
    @NotNull(message = "El id del detalle inventario es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor o igual a 1")
    private Long id;

    @NotNull(message = "El stock fisico es obligatorio")
    @Min(value = 0, message = "La cantidad debe ser mayor o igual a 0")
    private Integer stockFisico;

    @NotNull(message = "La diferencia  es obligatoria")
    @Min(value = 0, message = "La diferencia debe ser mayor o igual a 0")
    private Integer diferencia;

    @NotBlank(message = "La observacion no puede estar vacía")
    @Size(max = 255, message = "La observación debe tener máximo 255 caracteres")
    private String observacion;

    @NotNull(message = "La fecha de cirre del inventario no puede estar vacía")
    @FutureOrPresent(message = "La fecha de cirre del inventario no puede ser anterior a hoy")
    private LocalDateTime fechaFinInventario;
    
    @NotNull(message = "El campo nuevo es obligatorio")
    private Boolean nuevo;

    @NotNull(message = "El campo eeditado manual es obligatorio")
    private Boolean editadoManual;

    @NotNull(message = "El el estado es obligatorio")
    @EstadoValido
    private Integer estado;
}
