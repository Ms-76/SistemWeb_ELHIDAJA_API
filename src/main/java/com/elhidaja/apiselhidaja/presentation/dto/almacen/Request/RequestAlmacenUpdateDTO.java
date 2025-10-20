package com.elhidaja.apiselhidaja.presentation.dto.almacen.Request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "idLogin","id", "codigo", "descripcion" })
public class RequestAlmacenUpdateDTO {

    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El idAlmacen es obligatorio")
    @Min(value = 1, message = "El id del almacén debe ser mayor o igual a 1")
    private Long id;

    @NotBlank(message = "El código no puede estar vacío")
    @Pattern(regexp = "^[a-zA-Z0-9\\-\\s]+$", message = "El código del almacen solo puede contener letras, números, espacios y guiones")
    @Size(min = 2, max = 50, message = "El código debe tener entre 2 y 50 caracteres")
    private String codigo;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(min = 3, max = 255, message = "La descripción debe tener entre 3 y 255 caracteres")
    private String descripcion;
}
