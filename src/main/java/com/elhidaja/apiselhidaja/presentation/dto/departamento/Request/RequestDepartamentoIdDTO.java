package com.elhidaja.apiselhidaja.presentation.dto.departamento.Request;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RequestDepartamentoIdDTO {
    @NotNull(message = "El idDepartamento es obligatorio")
    @Min(value = 1, message = "El id del departamento debe ser mayor o igual a 1")
    private Long id;
}
