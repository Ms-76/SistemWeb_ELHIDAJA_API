package com.elhidaja.apiselhidaja.presentation.dto.tipoOperacion.Request;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestTipoOperacionInsertDTO {
    @NotBlank(message = "El nombre de la operación no puede estar vacío")
    @Size(min = 3, max = 50, message = "El nombre de la operación debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[^\\d]*$", message = "El nombre no puede contener números")
    private String nombre;

    @NotBlank(message = "La abreviatura no puede estar vacía")
    @Size(min = 2, max = 10, message = "La abreviatura debe tener entre 2 y 10 caracteres")
    @Pattern(regexp = "^[^\\d]*$", message = "La abreviatura no puede contener números")
    private String abreviatura;
}
