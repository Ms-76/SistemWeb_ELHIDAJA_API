package com.elhidaja.apiselhidaja.presentation.dto.usuario.Request;

import lombok.Data;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class RequestUsuarioOptionDTO {
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El idProvincia es obligatorio")
    @Min(value = 0, message = "El idProvincia debe ser mayor o igual a 0")
    private Long idProvincia;

    @NotNull(message = "El id del departamento es obligatorio")
    @Min(value = 0, message = "El id del departamento debe ser mayor o igual a 0")
    private Long idDepartamento;

    @NotNull(message = "El ID del distrito es obligatorio")
    @Min(value = 0, message = "ID de distrito debe ser mayor o igual a 0")
    private Integer idDistrito;

    @NotNull(message = "El ID del documento de identidad es obligatorio")
    @Min(value = 0, message = "ID de documento de identidad debe ser mayor o igual a 0")
    private Integer idDocumentoIdentidad;

    @NotNull(message = "El id_area es obligatorio")
    @Min(value = 0, message = "El id_area debe ser mayor o igual a 0")
    private Integer idArea;

    @NotNull(message = "El id_nivel_academico es obligatorio")
    @Min(value = 0, message = "El id_nivel_academico debe ser mayor o igual a 0")
    private Integer idNivelAcademico;

    @NotNull(message = "El id_oficio es obligatorio")
    @Min(value = 0, message = "El id_oficio debe ser mayor o igual a 0")
    private Integer idOficio;

    @NotNull(message = "El id_puesto es obligatorio")
    @Min(value = 0, message = "El id_puesto debe ser mayor o igual a 0")
    private Integer idPuesto;

    @NotNull(message = "El id_rol es obligatorio")
    @Min(value = 0, message = "El id_rol debe ser mayor o igual a 0")
    private Integer idRol;

}
