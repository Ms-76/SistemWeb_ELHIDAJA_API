package com.elhidaja.apiselhidaja.presentation.dto.departamento.Request;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDepartamentoUpdateDTO {
    @NotNull(message = "El idDepartamento es obligatorio")
    @Min(value = 1, message = "El id del departamento debe ser mayor o igual a 1")
    private Long id;

    @NotBlank(message = "El nombre del departamento no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre del departamento debe tener entre 3 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del departamento solo puede contener letras y espacios")
    private String nombre;
}
