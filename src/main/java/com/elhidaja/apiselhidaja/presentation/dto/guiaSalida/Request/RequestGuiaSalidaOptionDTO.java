package com.elhidaja.apiselhidaja.presentation.dto.guiaSalida.Request;

import java.time.LocalDate;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RequestGuiaSalidaOptionDTO {

    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotNull(message = "El id del almacen origen es obligatorio")
    @Min(value = 0, message = "El id del almacen origen debe ser mayor o igual 0")
    private Long idAlmacenOrigen;

    @NotBlank(message = "El tipo de destino es obligatorio")
    @Pattern(regexp = "TODOS|ALMACEN|PROYECTO", message = "El tipo de destino debe ser TODOS, ALMACEN o PROYECTO")
    private String tipoDestino;
    
    @NotNull(message = "El la fecha inicio es obligatorio")
    private LocalDate fechaInicio;

    @NotNull(message = "El la fecha fin es obligatorio")
    private LocalDate fechaFin;
}
