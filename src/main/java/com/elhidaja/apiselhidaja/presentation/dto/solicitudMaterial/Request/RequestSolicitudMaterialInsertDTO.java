package com.elhidaja.apiselhidaja.presentation.dto.solicitudMaterial.Request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonPropertyOrder({ "idLogin","id", "idSupervisor", "nombre", "descripcion", "xmlDetalles" })
public class RequestSolicitudMaterialInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El id del proyecto es obligatorio")
    @Min(value = 1, message = "El id del proyecto debe ser mayor o igual a 1")
    private Long idProyecto;

    @NotNull(message = "El id del supervisor es obligatorio")
    @Min(value = 1, message = "El id del supervisor debe ser mayor o igual a 1")
    private Long idSupervisor;

    @NotBlank(message = "El nombre de la solicitud no puede estar vacío")
    @Size(min = 3, max = 200, message = "El nombre de la solicitud debe tener entre 3 y 200 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String descripcion;

    @NotBlank(message = "Debe incluir los detalles en formato XML")
    private String xmlDetalles;

}
