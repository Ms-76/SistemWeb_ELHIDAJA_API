package com.elhidaja.apiselhidaja.presentation.dto.categoria.Resquest;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "nombre" })
public class RequestCategoriaInsertDTO {

    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "El nombre de la categoria no puede estar vacío")
    @LengthSQL(tabla = "categoria", columna = "nombre")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "el nombre de la categoria solo puede letras y espacios")
    private String nombre;

}
