package com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestGuiaEntradaInsertDTO {
    @NotNull(message = "El idProveedor es obligatorio")
    private Integer idProveedor;

    @NotNull(message = "El idUsuario es obligatorio")
    private Integer idUsuario;

    @Size(max = 450, message = "La descripción debe tener máximo 450 caracteres")
    private String descripcion;
}
