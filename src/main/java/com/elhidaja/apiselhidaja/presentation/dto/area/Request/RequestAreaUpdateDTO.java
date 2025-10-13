package com.elhidaja.apiselhidaja.presentation.dto.area.Request;

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
public class RequestAreaUpdateDTO {
    @NotNull(message = "El id del área es obligatorio")
    @Min(value = 1, message = "El id del área debe ser mayor o igual a 1")
    private Long id;

    @NotBlank(message = "El nombre del área no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre del área debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del área solo puede contener letras y espacios")
    private String nombre;
}
