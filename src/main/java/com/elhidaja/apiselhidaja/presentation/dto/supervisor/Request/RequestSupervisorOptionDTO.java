package com.elhidaja.apiselhidaja.presentation.dto.supervisor.Request;

import com.elhidaja.apiselhidaja.util.validationsPersonalisate.ValidOption;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class RequestSupervisorOptionDTO {
    @NotNull(message = "El estado es obligatorio")
    @ValidOption
    private Long estado;

    @NotBlank(message = "El tipo de supervisor es obligatorio")
    @Pattern(regexp = "TODOS|INGENIERO|ARQUITECTO", message = "El tipo de supervisor debe ser TODOS, INGENIERO o ARQUITECTO")
    private String tipoSupervisor;

}