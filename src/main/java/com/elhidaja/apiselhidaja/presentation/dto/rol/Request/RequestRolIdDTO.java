package com.elhidaja.apiselhidaja.presentation.dto.rol.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestRolIdDTO {
    @NotNull(message = "El idRol es obligatorio")
    @Min(value = 1, message = "El id del rol debe ser mayor o igual a 1")
    private Long id;
}
