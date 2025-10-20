package com.elhidaja.apiselhidaja.presentation.dto.maestroobra.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "id", "numeroDocumento", "nombres", "idDocumentoIdentidad", "direccion", "telefono",
        "email", "idDistrito", "experienciaAnios" })
public class RequestMaestroObraUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El id del maestro de obra es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor o igual a 1")
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
    @Pattern(regexp = "^9[0-9]{8}$", message = "El teléfono debe comenzar con 9 y contener exactamente 9 dígitos numéricos sin espacios")
    private String telefono;

    @NotBlank(message = "El email no puede estar vacío")
    @Size(max = 255, message = "El email no debe exceder 255 caracteres")
    @Email(message = "El email debe tener un formato válido")
    private String email;

    @NotNull(message = "El ID del distrito es obligatorio")
    @Min(value = 1, message = "ID de distrito no válido")
    private Integer idDistrito;

    @Min(value = 0, message = "La experiencia no puede ser negativa")
    private Integer experienciaAnios;
}
