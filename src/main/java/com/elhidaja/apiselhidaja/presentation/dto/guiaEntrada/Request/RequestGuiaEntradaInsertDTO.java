package com.elhidaja.apiselhidaja.presentation.dto.guiaEntrada.Request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "idProveedor", "idUsuario", "descripcion" })
public class RequestGuiaEntradaInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El idProveedor es obligatorio")
    private Integer idProveedor;

    @NotNull(message = "El idUsuario es obligatorio")
    private Integer idUsuario;

    @Size(max = 450, message = "La descripción debe tener máximo 450 caracteres")
    private String descripcion;
}
