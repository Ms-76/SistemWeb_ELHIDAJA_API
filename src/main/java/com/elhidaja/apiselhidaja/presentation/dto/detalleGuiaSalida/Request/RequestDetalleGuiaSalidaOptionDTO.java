package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaSalida.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RequestDetalleGuiaSalidaOptionDTO {

    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El id del almacen origen es obligatorio")
    @Min(value = 0, message = "El id del almacen debe ser mayor o igual a  0")
    private Long idAlmacenOrigen;

    @NotNull(message = "El id de la guia salida es obligatorio")
    @Min(value = 0, message = "El id del  la guia salida  ser mayor o igual a  0")
    private Long idGuiaSalida;

    @NotBlank(message = "El tipoDestino es obligatorio")
    @Pattern(regexp = "ALMACEN|PROYECTO|ALL", message = "tipoDestino debe ser ALMACEN, PROYECTO o ALL")
    private String tipoDestino = "ALL";
}
