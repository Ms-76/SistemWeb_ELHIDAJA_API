package com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin","id", "nombre" })
public class RequestTipoDocumentoUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El idTipoDocumento es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor o igual a 1")
    private Long id;

    @NotBlank(message = "El nombre del tipo de documento no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZÁÉÍÓÚáéíóúñÑ\\s]+$", message = "El nombre solo puede contener letras y espacios")
    private String nombre;
}
