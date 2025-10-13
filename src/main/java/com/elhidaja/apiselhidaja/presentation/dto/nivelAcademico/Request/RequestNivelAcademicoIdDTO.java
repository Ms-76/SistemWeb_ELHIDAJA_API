package com.elhidaja.apiselhidaja.presentation.dto.nivelAcademico.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class RequestNivelAcademicoIdDTO {
    @NotNull(message = "El idNivelAcademico es obligatorio")
    @Min(value = 1, message = "El id del nivel académico debe ser mayor o igual a 1")
    private Long id;
}
