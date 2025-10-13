package com.elhidaja.apiselhidaja.presentation.dto.area.Request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class RequestAreaIdDTO {
    @NotNull(message = "El id del área es obligatorio")
    @Min(value = 1, message = "El id del área debe ser mayor o igual a 1")
    private Long id;
}
