package com.elhidaja.apiselhidaja.presentation.dto.guiaTransporte.Request;

import java.time.LocalDate;
import lombok.*;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestGuiaTransporteUpdateDTO {

    @NotNull(message = "El idGuiaTransporte es obligatorio")
    private Integer idGuiaTransporte;

    @NotNull(message = "El idGuiaSalida es obligatorio")
    private Integer idGuiaSalida;

    @NotNull(message = "El idAsignacion es obligatorio")
    private Integer idAsignacion;

    @NotNull(message = "El idSerieDocumento es obligatorio")
    private Integer idSerieDocumento;

    @NotNull(message = "El idAlmacenSalida es obligatorio")
    private Integer idAlmacenSalida;

    private Integer puntoLlegada;

    @NotNull(message = "La fecha de traslado es obligatoria")
    private LocalDate fechaTranslado;

    @Size(max = 450, message = "La observación no puede exceder 450 caracteres")
    private String observacion;

    @NotNull(message = "El status es obligatorio")
    private Boolean status;
}