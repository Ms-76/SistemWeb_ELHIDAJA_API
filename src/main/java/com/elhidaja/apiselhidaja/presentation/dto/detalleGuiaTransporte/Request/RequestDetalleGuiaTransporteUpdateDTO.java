package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaTransporte.Request;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "idDetalleGuia", "idGuiaTransporte", "idProducto", "cantidad", "idUnidadMedida", "observacion", "status" })
public class RequestDetalleGuiaTransporteUpdateDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El idDetalleGuia es obligatorio")
    @Min(value = 1, message = "El idDetalleGuia debe ser mayor o igual a 1")
    private Long idDetalleGuia;

    @NotNull(message = "El idGuiaTransporte es obligatorio")
    @Min(value = 1, message = "El idGuiaTransporte debe ser mayor o igual a 1")
    private Long idGuiaTransporte;

    @NotNull(message = "El idProducto es obligatorio")
    @Min(value = 1, message = "El idProducto debe ser mayor o igual a 1")
    private Long idProducto;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor o igual a 1")
    private Integer cantidad;

    @NotNull(message = "El idUnidadMedida es obligatorio")
    @Min(value = 1, message = "El idUnidadMedida debe ser mayor o igual a 1")
    private Integer idUnidadMedida;

    private String observacion;

    @NotNull(message = "El status es obligatorio")
    private Boolean status;
}