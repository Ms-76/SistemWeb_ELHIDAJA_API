package com.elhidaja.apiselhidaja.presentation.dto.usuario.Request;

import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class RequestUsuarioIdDTO {

    @NotNull(message = "El idUsuario es obligatorio")
    @Min(value = 1, message = "El id del usuario debe ser mayor o igual a 1")
    private Long id;
}
