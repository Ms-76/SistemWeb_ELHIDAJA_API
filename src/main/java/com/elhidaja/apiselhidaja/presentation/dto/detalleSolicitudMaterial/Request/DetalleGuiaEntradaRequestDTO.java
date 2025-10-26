package com.elhidaja.apiselhidaja.presentation.dto.detalleSolicitudMaterial.Request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleGuiaEntradaRequestDTO {

    @NotNull(message = "El id del producto es obligatorio")
    @Min(value = 1, message = "El id del producto debe ser mayor o igual a 1")
    private Integer idProducto;

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

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor que cero")
    private Integer cantidad;

    @NotNull(message = "El id de la unidad de medida es obligatorio")
    @Min(value = 0, message = "El id de la unidad de medida debe ser mayor o igual a 1")
    private Integer idUnidadMedida;

    @NotBlank(message = "La observación es obligatoria")
    @LengthSQL(tabla = "detalle_guia_entrada", columna = "observacion")
    private String observacion;
}
