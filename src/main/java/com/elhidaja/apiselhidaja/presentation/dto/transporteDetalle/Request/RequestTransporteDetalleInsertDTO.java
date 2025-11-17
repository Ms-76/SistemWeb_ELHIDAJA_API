package com.elhidaja.apiselhidaja.presentation.dto.transporteDetalle.Request;

import lombok.Data;

import jakarta.validation.constraints.*;

@Data
public class RequestTransporteDetalleInsertDTO {

    @NotNull(message = "El idTransporte es obligatorio")
    @Min(value = 1, message = "El idTransporte debe ser mayor o igual a 1")
    private Long idTransporte;

    @NotNull(message = "El id del pasajero es obligatorio")
    @Min(value = 0, message = "El id del pasajero debe ser mayor o igual a 0")
    private Long idPasajero;

    @NotNull(message = "El id del tipo pasajero es obligatorio")
    @Min(value = 1, message = "El id del tipo pasajero debe ser mayor o igual a 1")
    private Long idTipoPasajero;

    @NotNull(message = "El costo del pasaje es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El costo del pasaje debe ser al menos 0.0")
    private Double costoPasaje;

    @NotNull(message = "El campo bulto es obligatorio")
    private Boolean bulto;

    @NotNull(message = "El costo del bulto es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El costo del bulto debe ser al menos 0.0")
    private Double costoBulto;
}
