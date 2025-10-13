package com.elhidaja.apiselhidaja.presentation.dto.nivelAcademico.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestNivelAcademicoInsertDTO {
    @NotBlank(message = "El nombre del nivel académico no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre del nivel académico debe tener entre 3 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del nivel académico debe contener solo letras y espacios")
    private String nombre;
}
