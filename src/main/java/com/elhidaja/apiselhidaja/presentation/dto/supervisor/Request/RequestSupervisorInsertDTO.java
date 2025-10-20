package com.elhidaja.apiselhidaja.presentation.dto.supervisor.Request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "idLogin", "idEntidad", "tipoSupervisor" })

public class RequestSupervisorInsertDTO {
    @NotNull(message = "El idLogin es obligatorio")
    @Min(value = 1, message = "El idLogin debe ser mayor o igual a 1")
    private Long idLogin;

    @NotNull(message = "El id_entidad es obligatorio")
    @Min(value = 1, message = "El id_entidad debe ser mayor o igual a 1")
    private Long idEntidad;

    @NotBlank(message = "El tipo de supervisor no puede estar vacío")
    @Pattern(regexp = "^(INGENIERO|ARQUITECTO)$", message = "El tipo de supervisor debe ser INGENIERO o ARQUITECTO")
    private String tipoSupervisor;
}
