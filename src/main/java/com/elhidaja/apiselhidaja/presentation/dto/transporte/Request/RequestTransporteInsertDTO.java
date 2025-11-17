package com.elhidaja.apiselhidaja.presentation.dto.transporte.Request;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import com.elhidaja.apiselhidaja.presentation.dto.transporteDetalle.Request.RequestTransporteDetalleInsertDTO;
import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;

import jakarta.validation.constraints.*;

@Data
public class RequestTransporteInsertDTO {

    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El idAsignacion es obligatorio")
    @Min(value = 1, message = "El idAsignacion debe ser mayor o igual a 1")
    private Long idAsignacion;

    @NotNull(message = "El id del tipo de transporte es obligatorio")
    @Min(value = 1, message = "El id del tipo de transporte debe ser mayor o igual a 1")
    private Long idTipoTransporte;

    @NotNull(message = "El id del tipo de origen es obligatorio")
    @Min(value = 1, message = "El id del tipo de origen debe ser mayor o igual a 1")
    private Long idTipoOrigen;

    @NotNull(message = "El id del origen es obligatorio")
    @Min(value = 0, message = "El id del origen debe ser mayor o igual a 0")
    private Long idOrigen;

    @NotNull(message = "El id del tipo de destino es obligatorio")
    @Min(value = 1, message = "El id del tipo de destino debe ser mayor o igual a 1")
    private Long idTipoDestino;

    @NotNull(message = "El id del destino es obligatorio")
    @Min(value = 0, message = "El id del destino debe ser mayor o igual a 0")
    private Long idDestino;

    @NotNull(message = "La fecha programada es obligatorio")
    @FutureOrPresent(message = "La fecha programada no puede ser menor a hoy")
    private LocalDate fechaProgramada;

    @NotBlank(message = "La observacion es obligatoria")
    @LengthSQL(tabla = "transporte", columna = "observaciones")
    private String observaciones;

    @NotEmpty(message = "Debe incluir al menos un detalle")
    private List<RequestTransporteDetalleInsertDTO> detalles;
}
