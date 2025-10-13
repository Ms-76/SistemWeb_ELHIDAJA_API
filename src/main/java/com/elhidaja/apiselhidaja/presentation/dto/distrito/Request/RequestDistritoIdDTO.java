package com.elhidaja.apiselhidaja.presentation.dto.distrito.Request;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class RequestDistritoIdDTO {
    @NotNull(message = "El idDistrito es obligatorio")
    @Min(value = 1, message = "El id del distrito debe ser mayor o igual a 1")
    private Long id;
}
