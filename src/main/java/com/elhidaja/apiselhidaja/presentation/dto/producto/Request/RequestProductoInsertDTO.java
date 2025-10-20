package com.elhidaja.apiselhidaja.presentation.dto.producto.Request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "codigo", "nombre", "imagen", "codigoBarras", "descripcion", "idSubcategoria",
        "costo" })
public class RequestProductoInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "El código del producto no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9\\-\\s]+$", message = "El código del producto solo puede contener letras, números, espacios y guiones")
    private String codigo;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del producto contener letras y espacios")
    private String nombre;

    private String imagen;

    @NotBlank(message = "El código de barras no puede estar vacío")
    private String codigoBarras;

    @NotBlank(message = "la descripcion  estar vacía")
    private String descripcion;

    @NotNull(message = "La subcategoría es obligatoria")
    @Min(value = 1, message = "ID de subcategoría no válido")
    private Long idSubcategoria;

    @NotNull(message = "El costo es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El costo debe ser mayor que cero")
    private BigDecimal costo;

}
