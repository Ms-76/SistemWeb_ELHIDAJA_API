package com.elhidaja.apiselhidaja.presentation.dto.tipoDocumento.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestTipoDocumentoInsertDTO {
    @NotBlank(message = "El nombre del tipo de documento no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZÁÉÍÓÚáéíóúñÑ\\s]+$", message = "El nombre solo puede contener letras y espacios")
    private String nombre;
}
