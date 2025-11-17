package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaTransporte.Request;

import lombok.Data;
import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class RequestDetalleGuiaTransporteOptionDTO {
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El idGuiaTransporte es obligatorio")
    @Min(value = 0, message = "El idGuiaTransporte debe ser mayor o igual a 0")
    private Long idGuiaTransporte;

    @NotNull(message = "El idProducto es obligatorio")
    @Min(value = 0, message = "El idProducto debe ser mayor o igual a 0")
    private Long idProducto;

    @NotNull(message = "El idAlmacen es obligatorio")
    @Min(value = 0, message = "El idAlmacen debe ser mayor o igual a 0")
    private Long idAlmacen;

    @NotNull(message = "El idUnidadMedida es obligatorio")
    @Min(value = 0, message = "El idUnidadMedida debe ser mayor o igual a 0")
    private Long idUnidadMedida;
}
