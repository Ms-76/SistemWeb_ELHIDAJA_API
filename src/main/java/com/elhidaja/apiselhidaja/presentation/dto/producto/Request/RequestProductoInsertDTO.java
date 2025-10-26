package com.elhidaja.apiselhidaja.presentation.dto.producto.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RequestProductoInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "El código del producto es obligatorio")
    @LengthSQL(tabla = "producto", columna = "codigo")
    private String codigo;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @LengthSQL(tabla = "producto", columna = "nombre")
    private String nombre;

    @LengthSQL(tabla = "producto", columna = "imagen")
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String imagen;

    @NotBlank(message = "El código de barras es obligatorio")
    @LengthSQL(tabla = "producto", columna = "codigo_barras")
    private String codigoBarras;

    @NotBlank(message = "La descripción del producto es obligatoria")
    @LengthSQL(tabla = "producto", columna = "descripcion")
    private String descripcionProd;

    @NotNull(message = "El id de la subcategoría es obligatorio")
    @Min(value = 1, message = "El id de la subcategoría debe ser mayor o igual a 1")
    private Integer idSubcategoria;

    @NotNull(message = "El costo del producto es obligatorio")
    @Positive(message = "El costo debe ser mayor que cero")
    private Double costo;

}
