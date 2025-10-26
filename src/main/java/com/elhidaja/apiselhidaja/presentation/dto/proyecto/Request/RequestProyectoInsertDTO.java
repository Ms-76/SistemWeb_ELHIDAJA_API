package com.elhidaja.apiselhidaja.presentation.dto.proyecto.Request;

import java.time.LocalDate;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "nombre", "descripcion", "ubicacion", "fechaInicio", "fechaFin", "idArquitecto",
        "idIngeniero", "idMaestroObra", "idSupervisor" })
public class RequestProyectoInsertDTO {

    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "El nombre del proyecto no puede estar vacío")
    @LengthSQL(tabla = "proyecto", columna = "nombre")
    private String nombre;

    @NotBlank(message = "La descripcion del proyecto no puede estar vacía")
    @LengthSQL(tabla = "proyecto", columna = "descripcion")
    private String descripcion;

    @NotBlank(message = "La ubicacion del proyecto no puede estar vacío")
    @LengthSQL(tabla = "proyecto", columna = "ubicacion")
    private String ubicacion;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @FutureOrPresent(message = "La fecha de inicio no puede ser una fecha pasada")
    private LocalDate fechaInicio;
    
    @NotNull(message = "La fecha de fin es obligatoria")
    @Future(message = "La fecha de fin debe ser una fecha futura")
    private LocalDate fechaFin;

    @NotNull(message = "El id del arquitecto es obligatorio")
    @Min(value = 1, message = "El id del arquitecto debe ser mayor o igual a 1")
    private Long idArquitecto;

    @NotNull(message = "El id del ingeniero es obligatorio")
    @Min(value = 1, message = "El id del ingeniero debe ser mayor o igual a 1")
    private Long idIngeniero;

    @NotNull(message = "El id del maestro de obra es obligatorio")
    @Min(value = 1, message = "El id del maestro de obra debe ser mayor o igual a 1")
    private Long idMaestroObra;

    @NotNull(message = "El id del supervisor es obligatorio")
    @Min(value = 1, message = "El id del supervisor debe ser mayor o igual a 1")
    private Long idSupervisor;
}
