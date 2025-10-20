package com.elhidaja.apiselhidaja.presentation.dto.documentoIdentidad.Request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "nombre", "descripcion", "longitud", "tipoDocumento" })
public class RequestDocumentoIdentidadInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 10, message = "El nombre debe tener máximo 10 caracteres")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "El nombre solo puede contener letras sin espacios")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 100, message = "La descripción debe tener máximo 100 caracteres")
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$", message = "La descripción solo puede contener letras y espacios")
    private String descripcion;

    @NotNull(message = "La longitud es obligatoria")
    @Min(value = 1, message = "La longitud debe ser mayor o igual a 1")
    private Integer longitud;

    @NotBlank(message = "El tipo de documento es obligatorio")
    @Pattern(regexp = "ALFABETICO|ALFANUMERICO|NUMERICO", message = "El tipo de documento debe ser ALFABETICO, ALFANUMERICO o NUMERICO")
    private String tipoDocumento;
}
