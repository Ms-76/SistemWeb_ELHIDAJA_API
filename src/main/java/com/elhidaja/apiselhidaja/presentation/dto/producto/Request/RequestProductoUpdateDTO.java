package com.elhidaja.apiselhidaja.presentation.dto.producto.Request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "id", "codigo", "nombre", "imagen", "codigoBarras", "descripcion", "idSubcategoria",
        "costo" })
public class RequestProductoUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El ID del producto es obligatorio")
    @Min(value = 1, message = "El ID del producto debe ser mayor que cero")
    private Long id;

    @NotBlank(message = "El código no puede estar vacío")
    private String codigo;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre del producto contener letras y espacios")
    private String nombre;

    private String imagen;

    @NotBlank(message = "El código de barras no puede estar vacío")
    private String codigoBarras;

    private String descripcion;

    @NotNull(message = "La subcategoría es obligatoria")
    @Min(value = 1, message = "ID de subcategoría no válido")
    private Long idSubcategoria;

    @NotNull(message = "El costo es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El costo debe ser mayor que cero")
    private BigDecimal costo;

}
