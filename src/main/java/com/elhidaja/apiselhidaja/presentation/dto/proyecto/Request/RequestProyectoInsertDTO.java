package com.elhidaja.apiselhidaja.presentation.dto.proyecto.Request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin",  "nombre", "descripcion", "ubicacion", "fechaInicio", "fechaFin", "idArquitecto",
        "idIngeniero", "idMaestroObra", "idSupervisor" })
public class RequestProyectoInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "El nombre del proyecto no puede estar vacío")
    @Size(min = 3, max = 200, message = "El nombre del proyecto debe tener entre 3 y 200 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String descripcion;

    @Size(max = 300, message = "La ubicación no puede exceder los 300 caracteres")
    private String ubicacion;

    private LocalDate fechaInicio;

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
