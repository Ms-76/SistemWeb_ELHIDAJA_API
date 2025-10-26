package com.elhidaja.apiselhidaja.presentation.dto.detalleGuiaSalida.Request;
import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@JsonPropertyOrder({ "estado", "idAlmacenOrigen", "tipoDestino"})
public class RequestDetalleGuiaSalidaOptionDTO {
    @NotNull(message = "El estado es obligatorio") @ValidOption
    private Long estado;
    @NotNull(message = "El id del almacen origen es obligatorio") @Min(value = 0, message = "El id del almacen debe ser >= 0")
    private Long idAlmacenOrigen;
    @NotBlank(message = "El tipoDestino es obligatorio")
    @Pattern(regexp = "ALMACEN|PROYECTO|ALL", message = "tipoDestino debe ser ALMACEN, PROYECTO o ALL")
    private String tipoDestino = "ALL";
}
