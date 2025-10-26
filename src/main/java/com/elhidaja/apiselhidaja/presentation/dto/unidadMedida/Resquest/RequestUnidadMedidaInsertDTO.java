package com.elhidaja.apiselhidaja.presentation.dto.unidadMedida.Resquest;

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
public class RequestUnidadMedidaInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "El nombre de la unidad de medida no puede estar vacío")
    @LengthSQL(tabla = "unidad_medida", columna = "nombre")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre debe contener solo letras y espacios")
    private String nombre;

}
