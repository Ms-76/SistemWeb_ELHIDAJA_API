package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaTransporte.Request;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.LengthSQL;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDetalleGuiaTransporteInsertDTO {

    @NotNull(message = "El idProducto es obligatorio")
    @Min(value = 1, message = "El idProducto debe ser mayor o igual a 1")
    private Long idProducto;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor o igual a 1")
    private Integer cantidad;

    @NotNull(message = "El idUnidadMedida es obligatorio")
    @Min(value = 1, message = "El idUnidadMedida debe ser mayor o igual a 1")
    private Integer idUnidadMedida;

    @NotBlank(message = "La observacion no puede estar vacía")
    @LengthSQL(tabla = "detalle_guia_transporte", columna = "descripcion")
    private String observacion;
}