package com.elhidaja.apiselhidaja.presentation.dto.inventario.Request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestInventarioUpdateDTO {
    
    @NotNull(message = "El id del inventario es obligatorio")
    @Min(value = 1, message = "El id del inventario debe ser mayor a 0")
    private Long id;

    @NotNull(message = "La fecha del inventario no puede estar vacía")
    @FutureOrPresent(message = "La fecha del inventario no puede ser anterior a hoy")
    private LocalDate fecha;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
    private String descripcion;

    @NotNull(message = "El id del usuario es obligatorio")
    @Min(value = 1, message = "El id del usuario debe ser mayor a 0")
    private Long idUsuario;
}
