package com.elhidaja.apiselhidaja.presentation.dto.pallet.Request;

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
@JsonPropertyOrder({ "idLogin", "id", "codigo", "descripcion", "idEstante" })
public class RequestPalletUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El id del pallet es obligatorio")
    @Min(value = 1, message = "El id del pallet debe ser mayor o igual a 1")
    private Long id;

    @NotBlank(message = "El código del pallet no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9\\-\\s]+$", message = "El código del pallet solo puede contener letras, números, espacios y guiones")
    @LengthSQL(tabla = "pallet", columna = "codigo")
    private String codigo;

    @NotBlank(message = "La descripcion pallet no puede estar vacía")
    @LengthSQL(tabla = "pallet", columna = "descripcion")
    private String descripcion;

    @NotNull(message = "El id del estante es obligatorio")
    @Min(value = 1, message = "El id del estante debe ser mayor o igual a 1")
    private Long idEstante;
}
