package com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Request;
import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@JsonPropertyOrder({ "estado", "idAlmacen"})
public class RequestGuiaSalidaOptionDTO {
    @NotNull(message = "El estado es obligatorio") @ValidOption
    private Long estado;
    @NotNull(message = "El id del almacen es obligatorio") @Min(value = 0, message = "El id del almacen debe ser >= 0")
    private Long idAlmacen;
}
