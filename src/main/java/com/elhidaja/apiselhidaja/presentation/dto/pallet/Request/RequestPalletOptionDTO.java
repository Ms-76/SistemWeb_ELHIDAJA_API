package com.elhidaja.apiselhidaja.presentation.dto.pallet.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonPropertyOrder({ "estado", "idAlmacen" })
public class RequestPalletOptionDTO {
    
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El id del almacen es obligatorio")
    @Min(value = 0, message = "El id del almacen debe ser mayor o igual a 0")
    private Long idAlmacen;

    @NotNull(message = "El id del estante es obligatorio")
    @Min(value = 0, message = "El id del estante debe ser mayor o igual a 0")
    private Long idEstante;
}
