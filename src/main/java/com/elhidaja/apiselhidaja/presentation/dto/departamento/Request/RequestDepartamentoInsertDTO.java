package com.elhidaja.apiselhidaja.presentation.dto.departamento.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "nombre" })
public class RequestDepartamentoInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;
    @NotBlank(message = "El nombre del departamento no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre del departamento debe tener entre 3 y 100 caracteres")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del departamento solo puede contener letras y espacios")
    private String nombre;
}
