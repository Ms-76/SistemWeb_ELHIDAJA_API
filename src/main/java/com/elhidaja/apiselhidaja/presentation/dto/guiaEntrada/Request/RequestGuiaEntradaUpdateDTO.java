package com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestGuiaEntradaUpdateDTO {

    @NotNull(message = "El idGuiaEntrada es obligatorio")
    @Min(value = 1, message = "El id debe ser mayor o igual a 1")
    private Long id;

    @Size(max = 450, message = "La descripción debe tener máximo 450 caracteres")
    private String descripcion;
}
