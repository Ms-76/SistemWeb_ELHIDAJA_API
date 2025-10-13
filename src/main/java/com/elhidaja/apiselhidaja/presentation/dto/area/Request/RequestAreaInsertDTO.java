package com.elhidaja.apiselhidaja.presentation.dto.area.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAreaInsertDTO {
    @NotBlank(message = "El nombre del área no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del área debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del área solo puede contener letras y espacios")
    private String nombre;
}
