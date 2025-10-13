package com.elhidaja.apiselhidaja.presentation.dto.documentoIdentidad.Request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDocumentoIdentidadUpdateDTO {
    @NotNull(message = "El id es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor o igual a 1")
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 10, message = "El nombre debe tener máximo 10 caracteres")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 100, message = "La descripción debe tener máximo 100 caracteres")
    private String descripcion;

    @NotNull(message = "La longitud es obligatoria")
    @Min(value = 1, message = "La longitud debe ser mayor o igual a 1")
    private Integer longitud;

    @NotBlank(message = "El tipo de documento es obligatorio")
    @Pattern(regexp = "ALFABETICO|ALFANUMERICO|NUMERICO", message = "El tipo de documento debe ser ALFABETICO, ALFANUMERICO o NUMERICO")
    private String tipoDocumento;
}
