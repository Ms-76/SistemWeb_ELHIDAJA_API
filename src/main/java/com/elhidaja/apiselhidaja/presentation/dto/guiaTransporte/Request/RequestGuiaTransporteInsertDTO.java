package com.elhidaja.apiselhidaja.presentation.dto.guiaTransporte.Request;

import java.time.LocalDate;
import java.util.List;

import com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaTransporte.Request.RequestDetalleGuiaTransporteInsertDTO;
import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestGuiaTransporteInsertDTO {

    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El idGuiaSalida es obligatorio")
    @Min(value = 1, message = "El idGuiaSalida debe ser mayor o igual a 1")
    private Integer idGuiaSalida;

    @NotNull(message = "El idAsignacion es obligatorio")
    @Min(value = 1, message = "El idAsignacion debe ser mayor o igual a 1")
    private Integer idAsignacion;

    @NotNull(message = "El idTipoOperacion es obligatorio")
    @Min(value = 1, message = "El idTipoOperacion debe ser mayor o igual a 1")
    private Integer idTipoOperacion;

    @NotNull(message = "El idTipoDocumento es obligatorio")
    @Min(value = 1, message = "El idTipoDocumento debe ser mayor o igual a 1")
    private Integer idTipoDocumento;

    @NotNull(message = "El codigo sunat es obligatorio")
    private Integer codigoSunat;  

    @NotNull(message = "El codigo interno es obligatorio")
    private Integer codigoInterno;  

    @NotNull(message = "El idSerie es obligatorio")
    @Min(value = 1, message = "El idSerie debe ser mayor o igual a 1")
    private Integer idSerie;

    @NotNull(message = "El idAlmacenSalida es obligatorio")
    @Min(value = 1, message = "El idAlmacenSalida debe ser mayor o igual a 1")
    private Integer idAlmacenSalida;

    @NotNull(message = "El id del punto de llegada es obligatorio")
    @Min(value = 1, message = "El puntoLlegada debe ser mayor o igual a 1")
    private Integer puntoLlegada;

    @NotNull(message = "La fecha de traslado es obligatoria")
    @FutureOrPresent(message = "La fecha de traslado no puede ser menor a hoy")
    private LocalDate fechaTranslado;

    @NotNull(message = "La Observacion es obligatoria")
    @LengthSQL(tabla = "guia_transporte", columna = "observacion")
    private String observacion;

    @NotEmpty(message = "Debe incluir al menos un detalle")
    private List<RequestDetalleGuiaTransporteInsertDTO> detalles;
}