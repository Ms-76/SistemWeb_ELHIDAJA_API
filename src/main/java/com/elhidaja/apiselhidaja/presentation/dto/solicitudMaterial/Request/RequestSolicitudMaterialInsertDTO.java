package com.elhidaja.apiselhidaja.presentation.dto.solicitudMaterial.Request;

import java.util.List;

import com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Request.RequestDetalleSolicitudMaterialInsert;
import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonPropertyOrder({ "idLogin", "id", "idSupervisor", "nombre", "descripcion", "xmlDetalles" })
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
    @LengthSQL(tabla = "solicitud_material", columna = "nombre")
    private String nombre;

    @NotBlank(message = "La descripcion no de la solicitud no puede estar vacía")
    @LengthSQL(tabla = "solicitud_material", columna = "descripcion")
    private String descripcion;

    @NotEmpty(message = "Debe incluir al menos un detalle")
    private List<RequestDetalleSolicitudMaterialInsert> detalles;

}
