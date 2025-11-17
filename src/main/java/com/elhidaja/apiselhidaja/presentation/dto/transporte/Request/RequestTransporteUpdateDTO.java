package com.elhidaja.apiselhidaja.presentation.dto.transporte.Request;

import java.time.LocalDate;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class RequestTransporteUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El idAsignacion es obligatorio")
    @Min(value = 1, message = "El idAsignacion debe ser mayor o igual a 1")
    private Long idAsignacion;

    @NotBlank(message = "El tipoTransporte es obligatorio")
    @Pattern(regexp = "MATERIAL|PASAJEROS", message = "tipoTransporte inválido")
    @LengthSQL(tabla = "transporte", columna = "tipo_transporte")
    private String tipoTransporte;

    private Long idOrigen;

    @NotBlank(message = "El tipoOrigen es obligatorio")
    @Pattern(regexp = "ALMACEN|PROYECTO|EXTERNO", message = "tipoOrigen inválido")
    @LengthSQL(tabla = "transporte", columna = "tipo_origen")
    private String tipoOrigen;

    private Long idDestino;

    @NotBlank(message = "El tipoDestino es obligatorio")
    @Pattern(regexp = "ALMACEN|PROYECTO|EXTERNO", message = "tipoDestino inválido")
    @LengthSQL(tabla = "transporte", columna = "tipo_destino")
    private String tipoDestino;

    @FutureOrPresent(message = "La fecha programada no puede ser menor a hoy")
    private LocalDate fechaProgramada;

    @NotBlank(message = "La observacion es obligatoria")
    @LengthSQL(tabla = "transporte", columna = "observaciones")
    private String observaciones;

    @NotBlank(message = "El xml es olbigatorio")
    private String xmlDetalles;

}
