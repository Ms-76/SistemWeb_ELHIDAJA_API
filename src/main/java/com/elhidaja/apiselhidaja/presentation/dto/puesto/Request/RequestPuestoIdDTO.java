package com.elhidaja.apiselhidaja.presentation.dto.puesto.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class RequestPuestoIdDTO {

    @NotNull(message = "El idPuesto es obligatorio")
    @Min(value = 1, message = "El id del puesto debe ser mayor o igual a 1")
    private Long id;
}
