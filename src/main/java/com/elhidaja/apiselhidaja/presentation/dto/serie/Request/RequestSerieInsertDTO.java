package com.elhidaja.apiselhidaja.presentation.dto.serie.Request;

import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "serie", "descripcion" })
public class RequestSerieInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotBlank(message = "La serie no puede estar vacía")
    @Size(min = 1, max = 50, message = "La serie debe tener entre 1 y 50 caracteres")
    private String serie;

    @NotBlank(message = "La descripción no puede estar vacía")
    @Size(max = 255, message = "La descripción no debe exceder los 255 caracteres")
    private String descripcion;
}
