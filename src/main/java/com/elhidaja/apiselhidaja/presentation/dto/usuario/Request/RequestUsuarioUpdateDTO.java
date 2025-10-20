package com.elhidaja.apiselhidaja.presentation.dto.usuario.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.MayorDeEdad;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "id", "numeroDocumento", "nombres", "idDocumentoIdentidad", "direccion", "telefono",
        "email", "idDistrito", "idEstadoCivil", "fechaNacimiento", "idGenero", "password", "idArea", "idNivelAcademico",
        "idOficio", "idPuesto", "idRol" })
public class RequestUsuarioUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El id del usuario es obligatorio")
    @Min(value = 1, message = "El id del usuario debe ser mayor o igual a 1")
    private Long id;
    @NotBlank(message = "El número de documento no puede estar vacío")
    @Size(max = 50, message = "El número de documento no debe exceder 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z0-9\\-]+$", message = "El número de documento solo puede contener letras, números y guiones")
    private String numeroDocumento;

    @NotBlank(message = "Los nombres no pueden estar vacíos")
    @Size(max = 255, message = "Los nombres no deben exceder 255 caracteres")
    @Pattern(regexp = "^[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s]+$", message = "Los nombres solo deben contener letras y espacios")
    private String nombres;

    @NotNull(message = "El ID del documento de identidad es obligatorio")
    @Min(value = 1, message = "ID de documento de identidad no válido")
    private Integer idDocumentoIdentidad;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(max = 150, message = "La dirección no debe exceder 150 caracteres")
    private String direccion;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Size(max = 20, message = "El teléfono no debe exceder 20 caracteres")
    @Pattern(regexp = "^[0-9\\-\\s]+$", message = "El teléfono solo puede contener números, espacios y guiones")
    private String telefono;

    @NotBlank(message = "El email no puede estar vacío")
    @Size(max = 255, message = "El email no debe exceder 255 caracteres")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotNull(message = "El ID del distrito es obligatorio")
    @Min(value = 1, message = "ID de distrito no válido")
    private Integer idDistrito;

    @NotBlank(message = "El estado civil no puede estar vacío")
    @Pattern(regexp = "CASADO|DIVORCIADO|SOLTERO|VIUDO", message = "Estado civil inválido")
    private String idEstadoCivil;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @MayorDeEdad(message = "Debes ser mayor de 18 años")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El género no puede estar vacío")
    @Pattern(regexp = "FEMENINO|MASCULINO|OTRO", message = "Género inválido")
    private String idGenero;

    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;

    @NotNull(message = "El id_area es obligatorio")
    private Integer idArea;

    @NotNull(message = "El id_nivel_academico es obligatorio")
    private Integer idNivelAcademico;

    @NotNull(message = "El id_oficio es obligatorio")
    private Integer idOficio;

    @NotNull(message = "El id_puesto es obligatorio")
    private Integer idPuesto;

    @NotNull(message = "El id_rol es obligatorio")
    private Integer idRol;
}
